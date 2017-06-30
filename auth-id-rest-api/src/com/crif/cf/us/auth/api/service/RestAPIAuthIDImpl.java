package com.crif.cf.us.auth.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.crif.cf.us.common.utils.PropertiesFileReader;

public class RestAPIAuthIDImpl implements IRestAPIAuthID{
	private static Logger logger = Logger.getLogger("CFProxy");
    private final String AuthID_PROPERTY_FILE_NAME = "AuthID.properties";
    private PropertiesFileReader propFileReader;
    private Properties propsAuthID;
    
	private boolean useProxyFlag;
    private String API_Protocol;
    private String BASE_URL;
    private String URL_AUTHENTICATE_DOCUMENT;
    private String URL_MATCH_SELFIE;
    private String URL_GET_TRANSACTION_INFO;
    private String accountAccessKey_Value;
    private String secretToken_Value;
    
    public RestAPIAuthIDImpl() throws IOException{
    	logger.info("Enter Init RestAPIAuthIDImpl");
		this.propFileReader = new PropertiesFileReader();
    	this.propsAuthID = this.propFileReader.getPropValues(AuthID_PROPERTY_FILE_NAME);
    	if(this.propsAuthID.getProperty("http.proxy.flag").equals("1")){
        	this.useProxyFlag = true;
    	}
    	this.API_Protocol =  this.propsAuthID.getProperty("api.protocol");
    	this.BASE_URL = this.propsAuthID.getProperty("api.base.url");
    	this.URL_AUTHENTICATE_DOCUMENT =  this.propsAuthID.getProperty("api.authenticate_document");
    	this.URL_MATCH_SELFIE = this.propsAuthID.getProperty("api.match_selfie");
    	this.URL_GET_TRANSACTION_INFO =  this.propsAuthID.getProperty("api.get_transaction_info");
    	this.accountAccessKey_Value = this.propsAuthID.getProperty("api.accountAccessKey");
    	this.secretToken_Value = this.propsAuthID.getProperty("api.secretToken");
    	logger.info("Exit Init RestAPIAuthIDImpl" + this.toString());    	
    }
    
    
	@Override
	public String authDocument(String authDocumentRequest) {
		logger.info("Enter authDocument @ " + this.getClass().getSimpleName() + "\r\nAuthDocumentRequest: \r\n" + authDocumentRequest.trim());
		try{
	        JSONObject requestJson = (JSONObject) new JSONParser().parse(authDocumentRequest.trim());
	        String documentType = requestJson.get(IAuthIDKeywords.Http_Header_DocumentType_Key).toString();
	        String base64StrFront = requestJson.get(IAuthIDKeywords.AuthID_DocumentImageBase64).toString();	        
	        // This needs to be discussed
			String clientRequestIdentifier = (new Date().getTime()/1000) + "" + Math.random() * 500 + 1;
	        // This needs to be discussed

			
			// License Passport or ID2
			if(documentType.toUpperCase().equals(IAuthIDKeywords.AuthID_P)){
				documentType = IAuthIDKeywords.AuthID_Passport;
			}else if(documentType.toUpperCase().equals(IAuthIDKeywords.AuthID_ID2)){
				documentType = IAuthIDKeywords.AuthID_ID2;
			}else{
				documentType = IAuthIDKeywords.AuthID_License;
			}
//			HttpHost proxy = new HttpHost("10.110.17.6",8080,"http");
//			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            URIBuilder builder = new URIBuilder();
            // byte[] imageByteArray = decodeImage(base64StrFront);
            ByteArrayBody byteArrayBodyFront = new ByteArrayBody(decodeImage(base64StrFront), IAuthIDKeywords.AuthID_Front +"." + IAuthIDKeywords.Img_Extension);
            HttpClient httpClient = HttpClientBuilder.create().build();
            builder.setScheme(this.API_Protocol).setHost(BASE_URL).setPath(URL_AUTHENTICATE_DOCUMENT);
            URI uri = builder.build();
            HttpPost postRequest = new HttpPost(uri);
            
            if(this.useProxyFlag){
                postRequest.setConfig(this.useProxy());
            }
            
            postRequest.setProtocolVersion(HttpVersion.HTTP_1_1);
            MultipartEntityBuilder mpEntity = MultipartEntityBuilder.create();
            mpEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            mpEntity.addPart(IAuthIDKeywords.AuthID_Front, byteArrayBodyFront);
            // Document Back Image is Optional by AuthID API
            if(requestJson.get(IAuthIDKeywords.AuthID_DocumentImageBase64Back) != null){
              ByteArrayBody byteArrayBodyBack = new ByteArrayBody(decodeImage(requestJson.get(IAuthIDKeywords.AuthID_DocumentImageBase64Back).toString()), IAuthIDKeywords.AuthID_Back +"." + IAuthIDKeywords.Img_Extension);
              mpEntity.addPart(IAuthIDKeywords.AuthID_Back, byteArrayBodyBack);
            }
            postRequest.setEntity(mpEntity.build());
            
            postRequest.setHeader(IAuthIDKeywords.Http_Header_Accept_Key, IAuthIDKeywords.Http_Header_Accept_Value);
            postRequest.setHeader(IAuthIDKeywords.Http_Header_AccountAccessKey_Key, this.accountAccessKey_Value);
            postRequest.setHeader(IAuthIDKeywords.Http_Header_SecretToken_Key, this.secretToken_Value);
            postRequest.setHeader(IAuthIDKeywords.Http_Header_RequestIdentifier_Key, clientRequestIdentifier);  // this is the unique reference for this request which the server will send back in the response.
            postRequest.setHeader(IAuthIDKeywords.Http_Header_DocumentType_Key, documentType); // Passport or License or ID2
            postRequest.setHeader(IAuthIDKeywords.Http_Header_DeviceDetails_Key, IAuthIDKeywords.Http_Header_DeviceDetails_Value);
            postRequest.setHeader(IAuthIDKeywords.Http_Header_AutoCrop_Key, IAuthIDKeywords.Http_Header_AutoCrop_Value);
            
            HttpResponse response = httpClient.execute(postRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("Authenticate Document response Status Code : " + statusCode);
            if (statusCode == IAuthIDKeywords.Http_Status_200_Code) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), IAuthIDKeywords.Char_Encoding));
                String sResponse;
                StringBuilder s = new StringBuilder();
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                logger.info("AuthID API Response: \r\n" + s.toString().trim());
                logger.info("Exit authDocument @" + this.getClass().getSimpleName());
                return s.toString().trim();
            } else {
            	logger.error("ERROR Authenticating Document");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), IAuthIDKeywords.Char_Encoding));
                String sResponse;
                StringBuilder s = new StringBuilder();
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                logger.error("AuthID API Error Response: \r\n" + s.toString().trim());
                logger.info("Exit authDocument @" + this.getClass().getSimpleName());
                return s.toString().trim();
            }
		}catch(Exception ex){
			// ex.printStackTrace();
			logger.info(ex.getMessage());
			return ex.getMessage();
		}
	}
	
	@Override
	public String getTransInformation(String transactionID) {		
		try{
			logger.info("Enter GetTransInformation TransactionID: \r\n" + transactionID);
			String transactionId= transactionID;
			URIBuilder builder = new URIBuilder();
            builder.setScheme(this.API_Protocol).setHost(BASE_URL).setPath(URL_GET_TRANSACTION_INFO).setParameter(IAuthIDKeywords.AuthID_TransactionId, transactionId);
            URI uri = builder.build();
            HttpGet getRequest = new HttpGet(uri);

            getRequest.setHeader(IAuthIDKeywords.Http_Header_Accept_Key, IAuthIDKeywords.Http_Header_Accept_Value);
            getRequest.setHeader(IAuthIDKeywords.Http_Header_AccountAccessKey_Key, this.accountAccessKey_Value);
            getRequest.setHeader(IAuthIDKeywords.Http_Header_SecretToken_Key, this.secretToken_Value);
            
            HttpClient httpClient = HttpClientBuilder.create().build();
//            ArrayList nameValuePairs = new ArrayList();
//            nameValuePairs.add(new BasicNameValuePair("TransactionId", transactionId)); // Transaction Id for which details are to be retrieved
//            HttpPost postRequest = new HttpPost(uri);
//            postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//            postRequest.setProtocolVersion(HttpVersion.HTTP_1_1);
            
            if(this.useProxyFlag){
                getRequest.setConfig(this.useProxy());
            }

            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == IAuthIDKeywords.Http_Status_200_Code) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), IAuthIDKeywords.Char_Encoding));
            	StringBuilder s = new StringBuilder();
            	String sResponse;
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                logger.info(s.toString().trim());
                return s.toString().trim();
            } else {
            	logger.error("ERROR Getting Transaction");
            	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), IAuthIDKeywords.Char_Encoding));
            	StringBuilder s = new StringBuilder();
                s = new StringBuilder();
                String sResponse;
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                logger.error(s.toString().trim());
                return s.toString().trim();
            }
		}catch(Exception ex){
			logger.error(ex.getMessage());
			return ex.getMessage();
		}
	}
	public static byte[] decodeImage(String imageDataString) {
		return org.apache.commons.codec.binary.Base64.decodeBase64(imageDataString);
	}
	
	private RequestConfig useProxy(){
		HttpHost proxy = new HttpHost(this.propsAuthID.getProperty("http.proxy.url"), Integer.parseInt(this.propsAuthID.getProperty("http.proxy.port")),
									this.propsAuthID.getProperty("http.proxy.protocol"));
		return RequestConfig.custom().setProxy(proxy).build();
	}
	
	@Override
	public String matchSelfie(String matchSelfieRequest) {
		logger.info(matchSelfieRequest);
		try{
	        JSONObject requestJson = (JSONObject) new JSONParser().parse(matchSelfieRequest.trim());
	        String base64SelfieStr = requestJson.get(IAuthIDKeywords.AuthID_SelfieBase64).toString();	 
	     	String transactionId = requestJson.get(IAuthIDKeywords.AuthID_TransactionId).toString();
			URIBuilder builder = new URIBuilder();
            builder.setScheme(this.API_Protocol).setHost(BASE_URL).setPath(URL_MATCH_SELFIE);
            URI uri = builder.build();
            HttpPost postRequest = new HttpPost(uri);
            if(this.useProxyFlag){
                postRequest.setConfig(this.useProxy());
            }
            postRequest.setHeader(IAuthIDKeywords.Http_Header_Accept_Key, IAuthIDKeywords.Http_Header_Accept_Value);
            postRequest.setHeader(IAuthIDKeywords.Http_Header_AccountAccessKey_Key, this.accountAccessKey_Value);
            postRequest.setHeader(IAuthIDKeywords.Http_Header_SecretToken_Key, this.secretToken_Value);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("TransactionId", transactionId));
            nameValuePairs.add(new BasicNameValuePair("SelfieBase64", base64SelfieStr));
            postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpClient httpClient = HttpClientBuilder.create().build();
            postRequest.setProtocolVersion(HttpVersion.HTTP_1_1);
            HttpResponse response = httpClient.execute(postRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), IAuthIDKeywords.Char_Encoding));
            	StringBuilder s = new StringBuilder();
            	String sResponse;
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                return s.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), IAuthIDKeywords.Char_Encoding));
                StringBuilder s = new StringBuilder();
                String sResponse;
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                return s.toString();
            }
        }catch(Exception ex){
        	ex.printStackTrace();
        	return null;
        }
        
	}

	@Override
	public String toString() {
		return "RestAPIAuthIDImpl [useProxyFlag=" + useProxyFlag + ", API_Protocol=" + API_Protocol + ", BASE_URL="
				+ BASE_URL + ", URL_AUTHENTICATE_DOCUMENT=" + URL_AUTHENTICATE_DOCUMENT + ", URL_MATCH_SELFIE="
				+ URL_MATCH_SELFIE + ", URL_GET_TRANSACTION_INFO=" + URL_GET_TRANSACTION_INFO
				+ ", accountAccessKey_Value=" + accountAccessKey_Value + ", secretToken_Value=" + secretToken_Value
				+ ", AuthID_PROPERTY_FILE_NAME=" + AuthID_PROPERTY_FILE_NAME + ", propFileReader=" + propFileReader
				+ ", propsAuthID=" + propsAuthID + "]";
	}

	@Override
	public String test() {
		return "{ \"Test Function\": \"Success\"}";
	}


	@Override
	public String filterAuthDocResponse(String authDocResponse) throws Exception{
		String returnStr = "";
		JSONParser  parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(authDocResponse);
		String statusStr = (String)jsonObj.get(IAuthIDKeywords.Auth_Response_Status);
		returnStr += "\"" + IAuthIDKeywords.Auth_Response_Status + "\": " + "\"" + statusStr + "\",";
		String TransactionIdStr = (String)jsonObj.get(IAuthIDKeywords.Auth_Response_TransactionId);
		returnStr += "\"" + IAuthIDKeywords.Auth_Response_TransactionId + "\": " + "\"" + TransactionIdStr + "\",";
		JSONObject DocumentInfoObj = (JSONObject)jsonObj.get(IAuthIDKeywords.Auth_Response_DocumentInfo);
		String resultStr = (String)DocumentInfoObj.get(IAuthIDKeywords.Auth_Response_Result);
		returnStr += "\"" + IAuthIDKeywords.Auth_Response_Result + "\": " + "\"" + resultStr + "\",";
		
		JSONObject DocumentClassificationObj = (JSONObject) DocumentInfoObj.get(IAuthIDKeywords.Auth_Response_DocumentClassification);
		returnStr += "\"" + IAuthIDKeywords.Auth_Response_DocumentClassification + "\": " + DocumentClassificationObj + ",";
		
		JSONObject DocumentFieldsObj = (JSONObject) DocumentInfoObj.get(IAuthIDKeywords.Auth_Response_DocumentFields);
		JSONObject RECObj = (JSONObject) DocumentFieldsObj.get(IAuthIDKeywords.Auth_Response_REC);
		returnStr += "\"" + IAuthIDKeywords.Auth_Response_DocumentFields + "\":" + RECObj + ",";

		JSONObject DocumentAlertsObj = (JSONObject) DocumentInfoObj.get(IAuthIDKeywords.Auth_Response_DocumentAlerts);
		returnStr += "\"" + IAuthIDKeywords.Auth_Response_DocumentAlerts + "\":" + DocumentAlertsObj + ",";
		returnStr = "{" + returnStr + "}";
		return returnStr;
	}


	@Override
	public String filterMatchSelfieResponse(String matchSelfieResponse) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String filterGetTransInfoResponse(String getTransInfoResponse) throws Exception{
		// AirApi 1.1 AuthDoc and GetTransInfo Responses are same
		return this.filterAuthDocResponse(getTransInfoResponse);
	}
}
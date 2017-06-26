package us.supercheng.crif.auth.app;

/**
 * Created by hongyu on 6/5/17.
 */


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.misc.IOUtils;

public class AirApiClient {

    public static final String SCHEME = "https";
    public static final String BASE_URL = "stage.coredev.catfishair.co"; // DNS NAME of the AppServer
    public static final String URL_AUTHENTICATE_DOCUMENT = "/AirApi/1.1/AuthenticateDocument";
    public static final String URL_MATCH_SELFIE = "/AirApi/1.1/MatchSelfie";
    public static final String URL_GET_TRANSACTION_INFO = "/AirApi/1.1/GetTransactionInfo";
    public static final String accountAccessKey = "a01fe10e315ed3d3";  // Replace with your access key
    public static final String secretToken = "e2f3a5c29df3b7a0";  // Replace with your secret token

    public static void main(String... args) {
        String tempRequestIdentifier = "3211232321";
        try {
            // Authenticate Document
            URIBuilder builder;
            // The scans should be in jpeg or png only
            File fileFront = new File("C:\\Users\\cl799honchen\\Desktop\\front.jpg"); //  Front of Drivers License
            // byte[] name = Base64.getEncoder().encode("hello World".getBytes());
            // byte[] decodedString = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));

            // Assume we get this datatype!
            String base64FrontStr = Base64.encode(FileUtils.readFileToByteArray(fileFront));

            File file = new File("C:\\Users\\cl799honchen\\Snap-On\\auth-id-rest-api\\test-res\\front.txt");
            String str = FileUtils.readFileToString(file, "utf-8");

            System.out.println(base64FrontStr);
            System.out.println(str);
            System.out.println(str.equals(base64FrontStr));

            byte[] imageByteArray = decodeImage(str);
            System.out.println(imageByteArray);
            // Assume we get this datatype!
//            File fileBack = new File("C:\\Users\\cl799honchen\\Desktop\\back.jpg"); // Optional , Back side of Drivers License, In case of passport this is not needed
            String base64SelfieStr = Base64.encode(FileUtils.readFileToByteArray(new File("C:\\Users\\cl799honchen\\Desktop\\selfie.jpg")));
            // System.out.println("Input Selfie: \r\n" + base64SelfieStr);

            // ByteArrayBody byteArrayBodyFront = new ByteArrayBody(FileUtils.readFileToByteArray(fileFront), fileFront.getName());
            ByteArrayBody byteArrayBodyFront = new ByteArrayBody(imageByteArray, fileFront.getName());



//            ByteArrayBody byteArrayBodyBack = new ByteArrayBody(FileUtils.readFileToByteArray(fileBack), fileBack.getName());
            HttpClient httpClient = HttpClientBuilder.create().build();


            builder = new URIBuilder();
            builder.setScheme(SCHEME).setHost(BASE_URL).setPath(URL_AUTHENTICATE_DOCUMENT);
            URI uri = builder.build();
            HttpPost postRequest = new HttpPost(uri);
            postRequest.setProtocolVersion(HttpVersion.HTTP_1_1);

            MultipartEntityBuilder mpEntity = MultipartEntityBuilder.create();
            mpEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            mpEntity.addPart("front", byteArrayBodyFront);
//            mpEntity.addPart("back", byteArrayBodyBack);
            postRequest.setEntity(mpEntity.build());

            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("AccountAccessKey", accountAccessKey);
            postRequest.setHeader("SecretToken", secretToken);
            postRequest.setHeader("RequestIdentifier", tempRequestIdentifier);  // this is the unique reference for this request which the server will send back in the response.
            postRequest.setHeader("DocumentType", "License"); // Passport or License or ID2
            postRequest.setHeader("DeviceDetails", "TestDevice");
            postRequest.setHeader("AutoCrop", "True"); // By default this is false, Not needed to be set if images are captured using our SDK

            HttpResponse response = httpClient.execute(postRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Authenticate Document response Status Code : " + statusCode);
            if (statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String sResponse;
                StringBuilder s = new StringBuilder();
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                JSONObject responseJson = (JSONObject) new JSONParser().parse(s.toString().trim());

                System.out.println("Transaction ID : " + responseJson.get("TransactionId"));

                String transactionId = responseJson.get("TransactionId").toString();

                System.out.println("Api Call Status : " + responseJson.get("Status")); // whether the Server was able to process the request
                System.out.println("Result : " + responseJson.get("Result"));
                System.out.println("Document ID : " + responseJson.get("DocumentId"));

                JSONObject documentInfoJson = (JSONObject) new JSONParser().parse(responseJson.get("DocumentInfo").toString());

                System.out.println("Document Classification : " + documentInfoJson.get("DocumentClassification"));
                System.out.println("Document Fields : " + documentInfoJson.get("DocumentFields"));
                System.out.println("Document Alerts : " + documentInfoJson.get("DocumentAlerts"));

                if (documentInfoJson.get("DocumentFields") != null) {
                    JSONObject documentFieldsJson = (JSONObject) new JSONParser().parse(documentInfoJson.get("DocumentFields").toString());

                    // Recomended Demographics Fields
                    System.out.println("REC : " + documentFieldsJson.get("REC"));
                    String base64photo = null;
                    String base64signature = null;
                    if (documentFieldsJson.get("REC") != null) {
                        JSONObject RECJson = (JSONObject) new JSONParser().parse(documentFieldsJson.get("REC").toString());
                        if (RECJson.containsKey("Photo")) {
                            base64photo = RECJson.get("Photo").toString(); // This is the headshot extracted from the document. Match Selfie should be initiated for this transaction, only when this field is not null
                        }
                        if (RECJson.containsKey("Signature")) {
                            base64signature = RECJson.get("Signature").toString();
                        }
                    }

                    System.out.println("SELFIE: \r\n" + base64photo);

                    // OCR Data
                    System.out.println("VIZ : " + documentFieldsJson.get("VIZ"));
                    // 2D Barcode Data
                    System.out.println("2D Barcode Data : " + documentFieldsJson.get("_2DBarcode"));

                    // Viz Native Data. If there are any Latin characters they will be returned as unicode.
                    System.out.println("VIZ Native : " + documentFieldsJson.get("VIZNative"));

                    // Match Selfie
                    if (base64photo != null) {
                        builder = new URIBuilder();
                        builder.setScheme(SCHEME).setHost(BASE_URL).setPath(URL_MATCH_SELFIE);
                        uri = builder.build();
                        postRequest = new HttpPost(uri);
                        postRequest.setHeader("Accept", "application/json");
                        postRequest.setHeader("AccountAccessKey", accountAccessKey);
                        postRequest.setHeader("SecretToken", secretToken);
                        postRequest.setHeader("RequestIdentifier", tempRequestIdentifier);  // this is the unique reference for this request which the server will send back in the response.

                        List<NameValuePair> nameValuePairs = new ArrayList();
                        nameValuePairs.add(new BasicNameValuePair("TransactionId", transactionId)); // Transaction Id against which the photo has to be matched
                        nameValuePairs.add(new BasicNameValuePair("SelfieBase64", base64SelfieStr)); // This has to be Live capture base 64 image, JPEG Format. In this sample we use the same image that we get back from authenticate document response.
                        postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                        httpClient = HttpClientBuilder.create().build();

                        // httpClient = new DefaultHttpClient();
                        // httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
                        postRequest.setProtocolVersion(HttpVersion.HTTP_1_1);

                        response = httpClient.execute(postRequest);
                        statusCode = response.getStatusLine().getStatusCode();
                        System.out.println("Match selfie response Status Code : " + statusCode);
                        if (statusCode == 200) {
                            reader = new BufferedReader(new InputStreamReader(
                                    response.getEntity().getContent(), "UTF-8"));
                            s = new StringBuilder();
                            while ((sResponse = reader.readLine()) != null) {
                                s = s.append(sResponse);
                            }
                            reader.close();
                            System.out.println(s.toString());
                        } else {
                            System.out.println("ERROR Matching Selfie");

                            reader = new BufferedReader(new InputStreamReader(
                                    response.getEntity().getContent(), "UTF-8"));
                            s = new StringBuilder();
                            while ((sResponse = reader.readLine()) != null) {
                                s = s.append(sResponse);
                            }
                            reader.close();
                            System.out.println(s.toString());

                            return;
                        }
                    }
                }
                // Get Transaction Info
                builder = new URIBuilder();
                builder.setScheme(SCHEME).setHost(BASE_URL).setPath(URL_GET_TRANSACTION_INFO).setParameter("TransactionId", transactionId);
                uri = builder.build();
                HttpGet getRequest = new HttpGet(uri);

                getRequest.setHeader("Accept", "application/json");
                getRequest.setHeader("AccountAccessKey", accountAccessKey);
                getRequest.setHeader("SecretToken", secretToken);
                ArrayList nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("TransactionId", transactionId)); // Transaction Id for which details are to be retrieved
                postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                // httpClient = new DefaultHttpClient();
                httpClient = HttpClientBuilder.create().build();
                // httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
                postRequest.setProtocolVersion(HttpVersion.HTTP_1_1);

                response = httpClient.execute(getRequest);
                statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Get Transaction Info response Status Code : " + statusCode);
                if (statusCode == 200) {
                    reader = new BufferedReader(new InputStreamReader(
                            response.getEntity().getContent(), "UTF-8"));
                    s = new StringBuilder();
                    while ((sResponse = reader.readLine()) != null) {
                        s = s.append(sResponse);
                    }
                    // System.out.println(s.toString());
                    reader.close();
                } else {
                    System.out.println("ERROR Getting Transaction");

                    reader = new BufferedReader(new InputStreamReader(
                            response.getEntity().getContent(), "UTF-8"));
                    s = new StringBuilder();
                    while ((sResponse = reader.readLine()) != null) {
                        s = s.append(sResponse);
                    }
                    System.out.println(s.toString());
                    reader.close();

                }
            } else {
                System.out.println("ERROR Authenticating Document");
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent(), "UTF-8"));
                String sResponse;
                StringBuilder s = new StringBuilder();
                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                reader.close();
                System.out.println("Response : " + s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] decodeImage(String imageDataString) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(imageDataString);
    }
}
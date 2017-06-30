package com.crif.cf.us.auth.api.json.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import com.crif.cf.us.auth.api.service.IAuthIDKeywords;
import com.crif.cf.us.common.utils.PropertiesFileReader;

public class MainJSONResponseTest {
	private PropertiesFileReader propertiesFileReader;
	
	public MainJSONResponseTest(){
		this.propertiesFileReader = new PropertiesFileReader();
	}

	@Test
	public void filterAuthDocJSONResponse() throws Exception {
		String content = this.propertiesFileReader.getStringFromInputStream(getClass().getResourceAsStream("/" + "AuthID-AuthDoc-Response-JSON.txt"));
		String returnStr = "";
		JSONParser  parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(content);
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
		System.out.println(returnStr);
		/*
		String ClassCodeStr = (String)DocumentClassificationObj.get("ClassCode");
		String ClassNameStr = (String)DocumentClassificationObj.get("ClassName");
		String IssueStr =(String)DocumentClassificationObj.get("Issue");
		String IssuerCodeStr =(String)DocumentClassificationObj.get("IssuerCode");
		String IssuerNameStr = (String)DocumentClassificationObj.get("IssuerName");
		String IssueTypeStr = (String)DocumentClassificationObj.get("IssueType");
		System.out.println("ClassCodeStr: " + ClassCodeStr);
		System.out.println("ClassName: " + ClassNameStr);
		System.out.println("IssueStr: " + IssueStr);
		System.out.println("IssuerCodeStr: " + IssuerCodeStr);
		System.out.println("IssuerNameStr: " + IssuerNameStr);
		System.out.println("IssueTypeStr: " + IssueTypeStr);

		RECObj.get("FullName");
		RECObj.get("Address");
		RECObj.get("AddressCity");
		RECObj.get("AddressLine1");
		RECObj.get("AddressPostalCode");
		RECObj.get("AddressState");
		RECObj.get("DocumentNumber");
		RECObj.get("BirthDate");
		RECObj.get("ExpirationDate");
		RECObj.get("GivenName");
		RECObj.get("IssueDate");
		RECObj.get("Surname");
		RECObj.get("Sex");
		RECObj.get("Height");
		RECObj.get("EyeColor");
		RECObj.get("IssuingStateCode");
		RECObj.get("IssuingStateName");
		RECObj.get("LicenseClass");
		RECObj.get("LicenseRestrictions");
		RECObj.get("Weight");
		RECObj.get("AddressVerified");
		RECObj.get("CardVersionNumber");
		RECObj.get("Race");
		RECObj.get("BirthPlace");
		RECObj.get("NationalityName");
		*/
	}
	
	@Test
	public void filterMatchSelfieJSONResponse() throws Exception {
		String content = this.propertiesFileReader.getStringFromInputStream(getClass().getResourceAsStream("/" + "AuthID-MatchSelfie-Response-JSON.txt"));
		System.out.println(content);
	}
}

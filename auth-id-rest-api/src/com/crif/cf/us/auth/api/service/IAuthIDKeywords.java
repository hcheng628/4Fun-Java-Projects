package com.crif.cf.us.auth.api.service;

public interface IAuthIDKeywords {
	public String Char_Encoding = "UTF-8";
	public String Img_Extension = "jpg";
	public int Http_Status_200_Code = 200;
	// AuthID Section --- Start
	public String AuthID_DocumentImageBase64 = "DocumentImageBase64";
	public String AuthID_DocumentImageBase64Back = "DocumentImageBase64Back";
	public String AuthID_SelfieBase64 = "SelfieBase64";
	public String AuthID_Passport = "Passport";
	public String AuthID_P = "P";
	public String AuthID_ID2 = "ID2";
	public String AuthID_License = "License";
	public String AuthID_Front = "front";
	public String AuthID_Back = "back";
	public String AuthID_TransactionId ="TransactionId";
	public String AuthID_DocumentInfo ="DocumentInfo";
	// AuthID Section --- End
	
	// Http Headers Section --- Start
	public String Http_Header_Accept_Key="Accept";
	public String Http_Header_Accept_Value="application/json";
	public String Http_Header_AccountAccessKey_Key = "AccountAccessKey";
	public String Http_Header_SecretToken_Key = "SecretToken";
	public String Http_Header_RequestIdentifier_Key = "RequestIdentifier";
	public String Http_Header_DocumentType_Key = "DocumentType";
	public String Http_Header_DeviceDetails_Key = "DeviceDetails";
	public String Http_Header_DeviceDetails_Value = "TestDevice";
	public String Http_Header_AutoCrop_Key = "AutoCrop";
	public String Http_Header_AutoCrop_Value = "True";
	// Http Headers Section --- End
	
	
	//Auth Response Key Words --- Start
	public String Auth_Response_Status = "Status";
	public String Auth_Response_TransactionId = AuthID_TransactionId;
	public String Auth_Response_DocumentInfo = "DocumentInfo";
	public String Auth_Response_Result = "Result";
	public String Auth_Response_DocumentClassification = "DocumentClassification";
	public String Auth_Response_DocumentFields = "DocumentFields";
	public String Auth_Response_REC = "REC";
	public String Auth_Response_DocumentAlerts = "DocumentAlerts";
	
	//Auth Response Key Words --- End
}
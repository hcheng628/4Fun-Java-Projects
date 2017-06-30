package com.crif.cf.us.auth.api.service.test;

import java.io.IOException;
import org.junit.Test;
import com.crif.cf.us.auth.api.service.RestAPIAuthIDImpl;
import com.crif.cf.us.common.utils.PropertiesFileReader;

public class MainAPITest {
	private PropertiesFileReader propertiesFileReader;
	private RestAPIAuthIDImpl api;
	
	public MainAPITest() throws Exception {
		this.propertiesFileReader = new PropertiesFileReader();
		this.api = new RestAPIAuthIDImpl();
	}

	@Test
	public void authDocument_Test() throws IOException {
		String content = this.propertiesFileReader.getStringFromInputStream(getClass().getResourceAsStream("/" + "AuthID-AuthDoc-Request-JSON.txt"));
		System.out.println(this.api.authDocument(content));
	}
	
	@Test
	public void authMatchSelfie_Test() throws IOException {
		String content = this.propertiesFileReader.getStringFromInputStream(getClass().getResourceAsStream("/" + "AuthID-MatchSelfie-Request-JSON.txt"));
		System.out.println(this.api.matchSelfie(content));
		
	}
}
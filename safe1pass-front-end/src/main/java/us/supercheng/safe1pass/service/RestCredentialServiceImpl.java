package us.supercheng.safe1pass.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import us.supercheng.safe1pass.util.RestAPIHelper;

import java.util.Properties;

/**
 * Created by cl799honchen on 7/25/2017.
 */
public class RestCredentialServiceImpl implements ICredentialService{

    private final String RestAPI_Content_Type = "application/x-www-form-urlencoded";
    private final String RestAPI_Key_BASEURL = "rest.api.endpoint.baseurl";
    private final String RestAPI_Key_LOGIN = "rest.api.endpoint.method.login";
    private final String RestAPI_Key_REGISTER = "rest.api.endpoint.method.register";

    private RestAPIHelper restAPIHelper;
    private Properties restAPIProp;

    public RestCredentialServiceImpl (Properties inAPIProp) {
        this.restAPIHelper = new RestAPIHelper();
        this.restAPIProp = inAPIProp;
    }

    @Override
    public String findUsername(String username) {
        // Server side needs to Implement this Method
        return null;
    }

    @Override
    public boolean login(String username, String password) {
        try {
            return Boolean.parseBoolean(this.restAPIHelper.rest_POST(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_REGISTER),
                    "username=" + username + "&password=" +password, RestAPI_Content_Type));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean createNewCredential(String username, char[] password, char[] confirm) {
        try {
            return Boolean.parseBoolean(this.restAPIHelper.rest_POST(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_LOGIN),
                    "username=" + username + "&password=" +new String(password), RestAPI_Content_Type));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

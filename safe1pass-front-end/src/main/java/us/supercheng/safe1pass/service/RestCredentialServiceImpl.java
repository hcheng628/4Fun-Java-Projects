package us.supercheng.safe1pass.service;

import us.supercheng.safe1pass.util.PasswordHelperImpl;
import us.supercheng.safe1pass.util.RestAPIHelper;
import java.util.Properties;

/**
 * Created by cl799honchen on 7/25/2017.
 */
public class RestCredentialServiceImpl implements ICredentialService{

    private final String RestAPI_Content_Type = "application/x-www-form-urlencoded";
    private final String RestAPI_Key_BASEURL = "rest.api.endpoint.baseurl";
    private final String RestAPI_Key_LOGIN = "rest.api.endpoint.method.login";
    private final String RestAPI_Key_FINDUSER = "rest.api.endpoint.method.finduser";
    private final String RestAPI_Key_REGISTER = "rest.api.endpoint.method.register";

    private RestAPIHelper restAPIHelper;
    private Properties restAPIProp;
    private PasswordHelperImpl passwordHelper;

    public RestCredentialServiceImpl (Properties inAPIProp) {
        this.restAPIHelper = new RestAPIHelper();
        this.restAPIProp = inAPIProp;
        this.passwordHelper = new PasswordHelperImpl();
    }

    public Properties getRestAPIProp() {
        return restAPIProp;
    }

    @Override
    public boolean login(String username, String password) {
        password = this.passwordHelper.encryptPassword(password);
        try {
            return Boolean.parseBoolean(this.restAPIHelper.rest_POST(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_LOGIN),
                    "username=" + username + "&password=" +password, RestAPI_Content_Type));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String findUsername(String username) {
        try {
            return this.restAPIHelper.rest_GET( this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_FINDUSER), "username=" + username);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean createNewCredential(String username, char[] password, char[] confirm) {
        String newPassword = this.passwordHelper.encryptPassword(new String(password));
        try {
            return Boolean.parseBoolean(this.restAPIHelper.rest_POST(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_REGISTER),
                    "username=" + username + "&password=" + newPassword, RestAPI_Content_Type));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

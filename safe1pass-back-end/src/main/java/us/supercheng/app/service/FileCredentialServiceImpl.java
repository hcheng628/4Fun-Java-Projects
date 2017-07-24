package us.supercheng.app.service;


import us.supercheng.app.util.FileHelperImpl;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by cl799honchen on 7/19/2017.
 */
public class FileCredentialServiceImpl implements ICredentialService{

    private final String PASS_RELATIVE_PATH = "/Password/Pass.properties";

    private FileHelperImpl fileHelper;
    private String appLocalPath;

    public FileCredentialServiceImpl (PropServiceImpl propServiceImpl) {
        try {
            this.fileHelper = new FileHelperImpl();
            this.appLocalPath = propServiceImpl.getAppLocalRootPath();
            System.out.println("appLocalPath: " + appLocalPath);
        } catch (Exception ex) {
            System.out.println("FileCredentialServiceImpl init Failed @" + this.getClass().getSimpleName());
            ex.printStackTrace();
        }

    }

    @Override
    public String findUsername(String username) {
        try{
            // Null = Not Found ; Else = Found this.getClass().getResourceAsStream(PASS_RELATIVE_PATH
            return this.fileHelper.getPropFileValueFromKey(username, new FileInputStream( this.appLocalPath + PASS_RELATIVE_PATH));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean login(String username, String password) {
        try{
            // Null = Not Found ; Else = Found
            String password_username = this.fileHelper.getPropFileValueFromKey(username,new FileInputStream(this.appLocalPath + PASS_RELATIVE_PATH));
            if(password_username != null && password_username.equals(password)) {
                return true;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

    @Override
    public boolean createNewCredential(String username, String password) {
        try {
            if(this.findUsername(username) == null) {
                this.fileHelper.savePropFile(username, password,new FileInputStream(this.appLocalPath + PASS_RELATIVE_PATH),
                        appLocalPath + PASS_RELATIVE_PATH);
                return true;
            } else {
                throw new RuntimeException("Sorry this username has been taken ;-( Try another one please ;-)");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matchConfirmPassword(char[] password, char[] confirm) {
        return new String(password).equals(new String(confirm)) ? true : false;
    }
}

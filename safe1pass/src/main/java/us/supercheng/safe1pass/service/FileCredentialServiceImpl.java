package us.supercheng.safe1pass.service;

import us.supercheng.safe1pass.util.FileHelperImpl;

/**
 * Created by cl799honchen on 7/19/2017.
 */
public class FileCredentialServiceImpl implements ICredentialService{

    private final String PASS_RELATIVE_PATH = "/Password/pass.properties";

    private FileHelperImpl fileHelper;

    public FileCredentialServiceImpl () {
        this.fileHelper = new FileHelperImpl();
    }

    @Override
    public String findUsername(String username) {
        try{
            // Null = Not Found ; Else = Found
            return this.fileHelper.getPropFileValueFromKey(username,this.getClass().getResource(PASS_RELATIVE_PATH).toURI().getPath());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean login(String username, String password) {
        try{
            // Null = Not Found ; Else = Found
            String password_username = this.fileHelper.getPropFileValueFromKey(username,this.getClass().getResource(PASS_RELATIVE_PATH).toURI().getPath());
            if(password_username != null && password_username.equals(password)) {
                return true;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

    @Override
    public boolean createNewCredential(String username, char[] password, char[] confirm) {
        try {
            if(this.findUsername(username) == null){
                if(this.matchConfirmPassword(password, confirm))     {
                    this.fileHelper.savePropFile(username, new String(password), this.getClass().getResource(PASS_RELATIVE_PATH).toURI().getPath());
                    System.out.println("Save Credential Successfully");
                    return true;
                } else {
                    System.out.println("Passwords not match");
                }
            } else {
                System.out.println("Sorry this username has been taken ;-( Try another one please ;-)");
            }
        } catch (Exception ex) {
            throw new  RuntimeException(ex);
        }
        return false;
    }

    @Override
    public boolean matchConfirmPassword(char[] password, char[] confirm) {
        return new String(password).equals(new String(confirm)) ? true : false;
    }
}

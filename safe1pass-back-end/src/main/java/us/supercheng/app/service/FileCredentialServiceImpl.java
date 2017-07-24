package us.supercheng.app.service;


import us.supercheng.app.util.FileHelperImpl;

/**
 * Created by cl799honchen on 7/19/2017.
 */
public class FileCredentialServiceImpl implements ICredentialService{

    private final String PASS_RELATIVE_PATH = "/Password/Pass.properties";

    private FileHelperImpl fileHelper;

    public FileCredentialServiceImpl () {
        this.fileHelper = new FileHelperImpl();
    }

    @Override
    public String findUsername(String username) {
        try{
            // Null = Not Found ; Else = Found this.getClass().getResourceAsStream(PASS_RELATIVE_PATH
            return this.fileHelper.getPropFileValueFromKey(username,this.getClass().getResourceAsStream(PASS_RELATIVE_PATH));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean login(String username, String password) {
        try{
            // Null = Not Found ; Else = Found
            String password_username = this.fileHelper.getPropFileValueFromKey(username,this.getClass().getResourceAsStream(PASS_RELATIVE_PATH));
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
                this.fileHelper.savePropFile(username, password, this.getClass().getResource(PASS_RELATIVE_PATH).toURI().getPath());
                return true;
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

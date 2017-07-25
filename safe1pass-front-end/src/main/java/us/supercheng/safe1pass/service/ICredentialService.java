package us.supercheng.safe1pass.service;

/**
 * Created by cl799honchen on 7/19/2017.
 */

public interface ICredentialService {
    String findUsername(String username);
    boolean login(String username, String password);
    boolean createNewCredential(String username, char[] password, char[] confirm);
}

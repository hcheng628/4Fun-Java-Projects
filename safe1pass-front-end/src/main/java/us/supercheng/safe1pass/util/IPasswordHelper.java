package us.supercheng.safe1pass.util;

/**
 * Created by cl799honchen on 8/11/2017.
 */
public interface IPasswordHelper {
    final String SHA_256 = "SHA-256";
    String encryptPassword(String password);
}
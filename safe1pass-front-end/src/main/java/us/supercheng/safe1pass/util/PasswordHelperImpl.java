package us.supercheng.safe1pass.util;

import java.security.MessageDigest;

/**
 * Created by cl799honchen on 8/11/2017.
 */
public class PasswordHelperImpl implements IPasswordHelper {

    @Override
    public String encryptPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            messageDigest.update(password.getBytes());
            byte [] bytePassword = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i=0;i<bytePassword.length;i++) {
                stringBuffer.append(Integer.toString((bytePassword[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<bytePassword.length;i++) {
                String hex = Integer.toHexString(0xff & bytePassword[i]);
//                if ((hex.length() == 1)) {
//                    hexString.append('0');
//                    hexString.append(hex);
//                }
                if(hex.length()==1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
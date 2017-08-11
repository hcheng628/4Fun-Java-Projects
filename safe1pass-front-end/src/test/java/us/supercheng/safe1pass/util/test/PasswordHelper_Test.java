package us.supercheng.safe1pass.util.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.safe1pass.util.FileHelperImpl;
import us.supercheng.safe1pass.util.PasswordHelperImpl;

import java.util.List;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class PasswordHelper_Test {
    final String password = "123456";

    private PasswordHelperImpl passwordHelper;

    @Before
    public void warmUp() {
        this.passwordHelper = new PasswordHelperImpl();
    }

    @Test
    public void readTxtFile_Test() throws Exception {
        String encryptPassword = this.passwordHelper.encryptPassword(this.password);
        System.out.println(encryptPassword);
    }
}
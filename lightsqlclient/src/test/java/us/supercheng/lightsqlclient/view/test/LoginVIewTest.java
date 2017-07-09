package us.supercheng.lightsqlclient.view.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.lightsqlclient.view.LoginView;

/**
 * Created by hongyu on 7/8/17.
 */
public class LoginVIewTest {
    private LoginView loginView;

    @Before
    public void warmUp(){
        this.loginView = new LoginView();
    }

    @Test
    public void loginView_Test(){
        loginView.setVisible(true);
        loginView.setResizable(false);
    }
}

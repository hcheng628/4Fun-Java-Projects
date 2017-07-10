package us.supercheng.lightsqlclient.service;

import us.supercheng.lightsqlclient.util.DBHelper;
import us.supercheng.lightsqlclient.view.LoginView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/9/17.
 */
public class LoginService implements ActionListener {
    private final String Login_Btn_Label = "Login";
    private final String Cancel_Btn_Label = "Cancel";

    private DBHelper dbHelper = null;
    private LoginView loginView;

    public LoginService(LoginView inLoginView){
        this.loginView = inLoginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if(button.getLabel().equals(Login_Btn_Label)){
            this.dbHelper = new DBHelper(this.loginView.getFields().get(3).getText(),new String(this.loginView.getPwField().getPassword()),
                    this.loginView.getFields().get(2).getText(),this.loginView.getFields().get(0).getText(),this.loginView.getFields().get(1).getText(),"mysql");
            //System.out.println(this.dbHelper.toString());
            this.dbHelper.connectDB();
        }else if(button.getLabel().equals(Cancel_Btn_Label)){
            System.exit(0);
        }
    }
}

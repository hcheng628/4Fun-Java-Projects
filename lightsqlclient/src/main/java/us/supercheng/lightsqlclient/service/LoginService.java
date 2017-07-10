package us.supercheng.lightsqlclient.service;

import us.supercheng.lightsqlclient.entity.IPanelNames;
import us.supercheng.lightsqlclient.entity.PanelManager;
import us.supercheng.lightsqlclient.util.DBHelper;
import us.supercheng.lightsqlclient.view.LoginView;
import javax.swing.*;
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
    private PanelManager panelMgmt;

    public LoginService(LoginView inLoginView, DBHelper inDBHelper, PanelManager inPanelMgmt){
        this.loginView = inLoginView;
        this.dbHelper = inDBHelper;
        this.panelMgmt = inPanelMgmt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if(button.getLabel().equals(Login_Btn_Label)){
            this.dbHelper.setDbUsername(this.loginView.getFields().get(3).getText());
            this.dbHelper.setDbPassword(new String(this.loginView.getPwField().getPassword()));
            this.dbHelper.setDbDB_SchemaName(this.loginView.getFields().get(2).getText());
            this.dbHelper.setDbURL(this.loginView.getFields().get(0).getText());
            this.dbHelper.setDbPort(this.loginView.getFields().get(1).getText());
            this.dbHelper.setDbDriverName("mysql");

            this.dbHelper.connectDB();
            if(this.dbHelper.isConnectionFlag()){
                JFrame mainFrame = (JFrame) SwingUtilities.windowForComponent((Button)e.getSource());
                mainFrame.remove(mainFrame.getContentPane());
                mainFrame.setContentPane(this.panelMgmt.getPanelTable().get(IPanelNames.DBClient_VIEW));
                mainFrame.pack();
                // System.out.println("Login Service: " + this.dbHelper + " " + this.dbHelper.isConnectionFlag());
            }
        }else if(button.getLabel().equals(Cancel_Btn_Label)){
            System.exit(0);
        }
    }
}
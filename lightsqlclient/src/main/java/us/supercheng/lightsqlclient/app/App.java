package us.supercheng.lightsqlclient.app;

import us.supercheng.lightsqlclient.entity.IPanelNames;
import us.supercheng.lightsqlclient.entity.PanelManager;
import us.supercheng.lightsqlclient.util.DBHelper;
import us.supercheng.lightsqlclient.view.DBClientPaneView;
import us.supercheng.lightsqlclient.view.LoginView;
import javax.swing.*;

/**
 * Created by hongyu on 7/8/17.
 */
public class App {

    public static void main(String[] args){
        PanelManager panelMgmt = new PanelManager();
        JFrame mainFrame = new JFrame();
        DBHelper dbHelper = new DBHelper();
        LoginView loginView = new LoginView(dbHelper, panelMgmt);
        DBClientPaneView dbClientPaneView = new DBClientPaneView(dbHelper);

        panelMgmt.addToPanelList(IPanelNames.LOGIN_VIEW, loginView);
        panelMgmt.addToPanelList(IPanelNames.DBClient_VIEW, dbClientPaneView);
        panelMgmt.addAllPanestoJFrame(mainFrame);

        mainFrame.setContentPane(loginView);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}
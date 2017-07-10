package us.supercheng.lightsqlclient.app;

import us.supercheng.lightsqlclient.util.DBHelper;
import us.supercheng.lightsqlclient.view.DBClientPaneView;
import us.supercheng.lightsqlclient.view.LoginView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/8/17.
 */
public class App {

    public static void main(String[] args){
        // new LoginView();

        JFrame frame = new JFrame();
        DBHelper dbHelper = new DBHelper("root","root","employees",
                "localhost","3306","mysql");
        dbHelper.connectDB();

        if(dbHelper.isConnectionFlag()){
            DBClientPaneView dbClientPaneView = new DBClientPaneView(dbHelper);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(dbClientPaneView);


            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // frame.setResizable(false);
        }
    }
}

package us.supercheng.safe1pass.app;

import us.supercheng.safe1pass.view.LoginView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hongyu on 7/16/17.
 */
public class App {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        LoginView loginView = new LoginView();

        mainFrame.add(loginView);

        mainFrame.setContentPane(loginView);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setPreferredSize(new Dimension(500,500));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }
}

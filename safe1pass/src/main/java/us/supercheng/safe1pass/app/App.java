package us.supercheng.safe1pass.app;

import us.supercheng.safe1pass.view.EditorView;
import us.supercheng.safe1pass.view.FileListView;
import us.supercheng.safe1pass.view.LoginView;
import us.supercheng.safe1pass.view.RegisterView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hongyu on 7/16/17.
 */
public class App {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Safe 1 Pass");
        LoginView loginView = new LoginView();
        mainFrame.add(loginView);
        mainFrame.setContentPane(loginView);

//        RegisterView registerView = new RegisterView();
//        mainFrame.add(registerView);
//        mainFrame.setContentPane(registerView);

//        FileListView fileListView = new FileListView();
//        mainFrame.add(fileListView);
//        mainFrame.setContentPane(fileListView);

//        EditorView editorView = new EditorView();
//        mainFrame.add(editorView);
//        mainFrame.setContentPane(editorView);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainFrame.setPreferredSize(new Dimension(600,600));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }
}

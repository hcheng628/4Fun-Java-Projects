package us.supercheng.safe1pass.app;

import us.supercheng.safe1pass.service.view.IServiceKeyword;
import us.supercheng.safe1pass.view.EditorView;
import us.supercheng.safe1pass.view.FileListView;
import us.supercheng.safe1pass.view.LoginView;
import us.supercheng.safe1pass.view.RegisterView;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by hongyu on 7/16/17.
 */

public class App extends Applet{

    public void init() {
        // g.drawString("Welcome in Java Applet.",500,500);
        this.setLayout(new CardLayout());
        // JFrame mainFrame = new JFrame("Safe 1 Pass");
        // JPanel mainPanel = new JPanel(new CardLayout());

        LoginView loginView = new LoginView(this);
        RegisterView registerView = new RegisterView(this);
        FileListView fileListView = new FileListView(this,"");
        EditorView editorView = new EditorView(this, "","");

        this.add(loginView, IServiceKeyword.LOGIN_VIEW);
        this.add(registerView, IServiceKeyword.REGISTER_VIEW);
        this.add(fileListView, IServiceKeyword.FILELIST_VIEW);
        this.add(editorView, IServiceKeyword.EDITOR_VIEW);

//        mainFrame.add(this);
//        mainFrame.setContentPane(this);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // mainFrame.setPreferredSize(new Dimension(600,600));
//        mainFrame.pack();
//        mainFrame.setLocationRelativeTo(null);
//        mainFrame.setVisible(true);
//        mainFrame.setResizable(false);
    }

        /*
    public void init(){
        JFrame mainFrame = new JFrame("Safe 1 Pass");
        JPanel mainPanel = new JPanel(new CardLayout());

        LoginView loginView = new LoginView(mainPanel);
        RegisterView registerView = new RegisterView(mainPanel);
        FileListView fileListView = new FileListView(mainPanel,"");
        EditorView editorView = new EditorView(mainPanel, "","");

        mainPanel.add(loginView, IServiceKeyword.LOGIN_VIEW);
        mainPanel.add(registerView, IServiceKeyword.REGISTER_VIEW);
        mainPanel.add(fileListView, IServiceKeyword.FILELIST_VIEW);
        mainPanel.add(editorView, IServiceKeyword.EDITOR_VIEW);

        mainFrame.add(mainPanel);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainFrame.setPreferredSize(new Dimension(600,600));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
    */
//    public static void main(String[] args) throws Exception{
//
//    }
}
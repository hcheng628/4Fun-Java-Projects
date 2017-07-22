package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.service.FileCredentialServiceImpl;
import us.supercheng.safe1pass.view.FileListView;
import us.supercheng.safe1pass.view.IViewKeyword;
import us.supercheng.safe1pass.view.LoginView;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */
public class LoginViewService implements ActionListener {

    private Applet mainPanel;
    private LoginView selfView;
    private FileCredentialServiceImpl fileCredentialService;

    public LoginViewService(Applet mainPanel, LoginView selfView) {
        this.selfView = selfView;
        this.mainPanel = mainPanel;
        this.fileCredentialService = new FileCredentialServiceImpl();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            String goToPanelName = "";
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_LOGIN)){
                if(this.fileCredentialService.login(this.selfView.getLoginUsernameTxt().getText().replaceAll("\\s+",""),
                        new String(this.selfView.getLoginPw().getPassword()))){
                    goToPanelName = IServiceKeyword.FILELIST_VIEW;
                    this.mainPanel.add(new FileListView(this.mainPanel, this.selfView.getLoginUsernameTxt().getText().replaceAll("\\s+","")), IServiceKeyword.FILELIST_VIEW);
                }
            }else if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_REGISTER)) {
                goToPanelName = IServiceKeyword.REGISTER_VIEW;

            }else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
            ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, goToPanelName);
        }
    }
}
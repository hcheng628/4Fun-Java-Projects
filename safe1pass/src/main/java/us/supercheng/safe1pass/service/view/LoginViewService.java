package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.view.IViewKeyword;
import us.supercheng.safe1pass.view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */
public class LoginViewService implements ActionListener {
    private JPanel mainPanel;
    private LoginView selfView;

    public LoginViewService(JPanel mainPanel, LoginView selfView) {
        this.selfView = selfView;
        this.mainPanel = mainPanel;
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof javax.swing.JButton){
            String goToPanelName = "";
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_LOGIN)){
                goToPanelName = IServiceKeyword.FILELIST_VIEW;
                this.selfView.getLoginUsernameTxt();
                this.selfView.getLoginPw();




            }else if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_REGISTER)) {
                goToPanelName = IServiceKeyword.REGISTER_VIEW;

            }else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
            ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, goToPanelName);
        }
    }
}
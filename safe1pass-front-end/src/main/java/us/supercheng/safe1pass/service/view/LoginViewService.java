package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.service.RestCredentialServiceImpl;
import us.supercheng.safe1pass.view.FileListView;
import us.supercheng.safe1pass.view.IViewKeyword;
import us.supercheng.safe1pass.view.LoginView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by hongyu on 7/16/17.
 */
public class LoginViewService implements ActionListener {

    private JPanel mainPanel;
    private LoginView selfView;
    private RestCredentialServiceImpl restCredentialService;


    public LoginViewService(JPanel mainPanel, LoginView selfView, Properties inAppProp) {
        this.selfView = selfView;
        this.mainPanel = mainPanel;
        this.restCredentialService = new RestCredentialServiceImpl(inAppProp);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            String goToPanelName = "";
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_LOGIN)){
                if(this.restCredentialService.login(this.selfView.getLoginUsernameTxt().getText().replaceAll("\\s+",""),
                        new String(this.selfView.getLoginPw().getPassword()))){
                    goToPanelName = IServiceKeyword.FILELIST_VIEW;
                    this.mainPanel.add(new FileListView(this.mainPanel, this.selfView.getLoginUsernameTxt().getText().replaceAll("\\s+",""), this.restCredentialService.getRestAPIProp()), IServiceKeyword.FILELIST_VIEW);
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
package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.service.FileCredentialServiceImpl;
import us.supercheng.safe1pass.view.IViewKeyword;
import us.supercheng.safe1pass.view.RegisterView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */

public class RegisterViewService implements ActionListener {
    private final String PASS_RELATIVE_PATH = "/Password/pass.properties";

    private JPanel mainPanel;
    private RegisterView selfView;
    private FileCredentialServiceImpl fileCredentialService;

    public RegisterViewService (JPanel mainPanel, RegisterView inSelfView) {
        this.mainPanel = mainPanel;
        this.selfView = inSelfView;
        this.fileCredentialService = new FileCredentialServiceImpl();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            String goToPanelName = "";
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.REGISTER_VIEW_CREATE)){
                goToPanelName = IServiceKeyword.LOGIN_VIEW;
                String newUsername = this.selfView.getRegisterTxt().getText().replaceAll("\\s+","");
                try {
                    if(this.fileCredentialService.createNewCredential(newUsername,this.selfView.getRegisterPws().get(0).getPassword(), this.selfView.getRegisterPws().get(1).getPassword())) {
                        ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, goToPanelName);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
        }
    }
}
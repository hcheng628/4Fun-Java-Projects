package us.supercheng.safe1pass.service;

import us.supercheng.safe1pass.util.FileHelperImpl;
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
    private FileHelperImpl fileHelper;

    public RegisterViewService (JPanel mainPanel, RegisterView inSelfView) {
        this.mainPanel = mainPanel;
        this.selfView = inSelfView;
        this.fileHelper = new FileHelperImpl();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            String goToPanelName = "";
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.REGISTER_VIEW_CREATE)){
                goToPanelName = IServiceKeyword.LOGIN_VIEW;
                String newUsername = this.selfView.getRegisterTxt().getText().replaceAll("\\s+","");
                try {
                    if(this.fileHelper.getPropFileValueFromKey(newUsername,this.getClass().getResource(PASS_RELATIVE_PATH).toURI().getPath()) == null){
                        if(new String(this.selfView.getRegisterPws().get(0).getPassword()).equals(new String(this.selfView.getRegisterPws().get(1).getPassword()))){
                            this.fileHelper.savePropFile(newUsername,new String(this.selfView.getRegisterPws().get(0).getPassword()), this.getClass().getResource(PASS_RELATIVE_PATH).toURI().getPath());
                            System.out.println("Save Successfully");
                            ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, goToPanelName);
                        } else {
                            System.out.println("Passwords not match");
                        }
                    }else {
                        System.out.println("Sorry this username has been taken ;-( Try another one please ;-)");
                    }



                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
            // ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, goToPanelName);
        }
    }
}
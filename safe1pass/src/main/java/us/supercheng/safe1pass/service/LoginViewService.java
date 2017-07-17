package us.supercheng.safe1pass.service;

import us.supercheng.safe1pass.view.IViewKeyword;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */
public class LoginViewService implements ActionListener {
    private JPanel mainPanel;

    public LoginViewService(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof javax.swing.JButton){
            String goToPanelName = "";
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_LOGIN)){
                //FileListView fileListView = new FileListView(this.mainPanel);
                goToPanelName = IServiceKeyword.FILELIST_VIEW;
            }else if(eventBtn.getText().equals(IViewKeyword.LOGIN_VIEW_REGISTER)) {
                //RegisterView registerView = new RegisterView(this.mainPanel);
                goToPanelName = IServiceKeyword.REGISTER_VIEW;
            }else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
            ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, goToPanelName);
        }
    }
}
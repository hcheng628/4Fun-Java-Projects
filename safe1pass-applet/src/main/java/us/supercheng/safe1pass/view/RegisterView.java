package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.view.RegisterViewService;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */
public class RegisterView extends JPanel {

    private Vector<JLabel> registerLabs;
    private JTextField registerTxt;
    private Vector<JPasswordField> registerPws;
    private JButton createAccountBtn;
    private JPanel registerPanel;
    private RegisterViewService registerViewService;

    public RegisterView(Applet mainPanel) {
        this.registerViewService = new RegisterViewService(mainPanel,this);
        this.registerLabs = new Vector<JLabel>();
        this.registerLabs.add(new JLabel(IViewKeyword.LOGIN_VIEW_USERNAME));
        this.registerLabs.add(new JLabel(IViewKeyword.LOGIN_VIEW_PASSWORD));
        this.registerLabs.add(new JLabel(IViewKeyword.REGISTER_VIEW_CONFIRM_PASSWORD));

        this.registerTxt = new JTextField();

        this.registerPws = new Vector<JPasswordField>();
        this.registerPws.add(new JPasswordField());
        this.registerPws.add(new JPasswordField());

        this.createAccountBtn = new JButton(IViewKeyword.REGISTER_VIEW_CREATE);
        this.createAccountBtn.addActionListener(this.registerViewService);

        this.registerPanel = new JPanel(new GridLayout(4,2));

        this.registerPanel.add(this.registerLabs.get(0));
        this.registerPanel.add(this.registerTxt);
        this.registerPanel.add(this.registerLabs.get(1));
        this.registerPanel.add(this.registerPws.get(0));
        this.registerPanel.add(this.registerLabs.get(2));
        this.registerPanel.add(this.registerPws.get(1));
        this.registerPanel.add(this.createAccountBtn);

        this.add(this.registerPanel);
    }

    public JTextField getRegisterTxt() {
        return registerTxt;
    }

    public Vector<JPasswordField> getRegisterPws() {
        return registerPws;
    }
}
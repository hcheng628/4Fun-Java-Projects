package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.view.LoginViewService;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */
public class LoginView extends JPanel {

    private LoginViewService loginViewService;

    private Vector<JButton> loginViewBtns;
    private Vector<JLabel> loginViewLabs;
    private JPasswordField loginPw;
    private JTextField loginUsernameTxt;
    private CardLayout loginLayout;
    private JPanel loginPanel;

    public LoginView(JPanel mainPanel) {
        this.loginLayout = (CardLayout) mainPanel.getLayout();
        this.setLayout(this.loginLayout);
        this.loginViewService = new LoginViewService(mainPanel, this);
        this.loginPanel = new JPanel(new GridLayout(3,2));

        this.loginViewBtns = new Vector<JButton>();
        this.loginViewBtns.add(new JButton(IViewKeyword.LOGIN_VIEW_LOGIN));
        this.loginViewBtns.get(0).addActionListener(this.loginViewService);

        this.loginViewBtns.add(new JButton(IViewKeyword.LOGIN_VIEW_REGISTER));
        this.loginViewBtns.get(1).addActionListener(this.loginViewService);

        this.loginViewLabs = new Vector<JLabel>();
        this.loginViewLabs.add(new JLabel(IViewKeyword.LOGIN_VIEW_USERNAME));
        this.loginViewLabs.add(new JLabel(IViewKeyword.LOGIN_VIEW_PASSWORD));

        this.loginPw = new JPasswordField();
        this.loginUsernameTxt = new JTextField();

        this.loginPanel.add(this.loginViewLabs.get(0));
        this.loginPanel.add(this.loginUsernameTxt);
        this.loginPanel.add(this.loginViewLabs.get(1));
        this.loginPanel.add(this.loginPw);
        this.loginPanel.add(this.loginViewBtns.get(0));
        this.loginPanel.add(this.loginViewBtns.get(1));
        this.loginPanel.setMaximumSize(new Dimension(200,300));
        this.setMaximumSize(new Dimension(200,300));

        this.add(this.loginPanel);
    }

    public JPasswordField getLoginPw() {
        return loginPw;
    }

    public JTextField getLoginUsernameTxt() {
        return loginUsernameTxt;
    }

    public CardLayout getLoginLayout() {
        return this.loginLayout;
    }
}
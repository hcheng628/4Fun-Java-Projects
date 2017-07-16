package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.LoginViewService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */
public class LoginView extends JPanel implements ActionListener {

    private LoginViewService loginViewService;

    private Vector<JButton> loginViewBtns;
    private Vector<JLabel> loginViewLabs;
    private JPasswordField loginPw;
    private JTextField loginUsernameTxt;
    private CardLayout loginLayout;

    public LoginView() {
        //this.loginLayout = new CardLayout();
        this.setLayout(new CardLayout());
        this.loginViewService = new LoginViewService();

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


        this.add(this.loginViewLabs.get(0));
        this.add(this.loginUsernameTxt);
        this.add(this.loginViewLabs.get(1));
        this.add(this.loginPw);
        this.add(this.loginViewBtns.get(0));
        this.add(this.loginViewBtns.get(1));
        this.setPreferredSize(new Dimension(500,500));
    }



    public void actionPerformed(ActionEvent e) {

    }
}

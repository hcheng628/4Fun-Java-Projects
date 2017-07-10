package us.supercheng.lightsqlclient.view;

import us.supercheng.lightsqlclient.service.LoginService;
import us.supercheng.lightsqlclient.util.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by hongyu on 7/8/17.
 */
public class LoginView extends JFrame{
    private Vector<JLabel> labels;
    private Vector<JTextField> fields;
    private JPasswordField pwField;
    private Vector<Button> buttons;
    private JPanel panel;
    private Font font;
    private DBHelper dbHelper;

    public LoginView(){
        this.font = new Font("SansSerif", Font.BOLD, 20);

        this.labels = new Vector<JLabel>();
        this.fields = new Vector<JTextField>();
        this.pwField = new JPasswordField();
        this.buttons = new Vector<Button>();

        this.labels.add(new JLabel("DB URL"));
        this.labels.add(new JLabel("DB Port"));
        this.labels.add(new JLabel("DB Name"));
        this.labels.add(new JLabel("Username"));
        this.labels.add(new JLabel("Password"));

        this.fields.add(new JTextField());
        this.fields.add(new JTextField());
        this.fields.add(new JTextField());
        this.fields.add(new JTextField());

        this.buttons.add(new Button("Login"));
        this.buttons.add(new Button("Cancel"));

        this.panel = new JPanel(new GridLayout(6,2));
        this.panel.add(this.labels.get(0));
        this.fields.get(0).setFont(this.font);
        this.panel.add(this.fields.get(0));

        this.panel.add(this.labels.get(1));
        this.fields.get(1).setFont(this.font);
        this.panel.add(this.fields.get(1));

        this.panel.add(this.labels.get(2));
        this.fields.get(2).setFont(this.font);
        this.panel.add(this.fields.get(2));

        this.panel.add(this.labels.get(3));
        this.fields.get(3).setFont(this.font);
        this.panel.add(this.fields.get(3));

        this.panel.add(this.labels.get(4));
        this.pwField.setFont(this.font);
        this.panel.add(this.pwField);

        this.panel.add(this.buttons.get(0));
        this.panel.add(this.buttons.get(1));

        this.panel.setPreferredSize(new Dimension(400,300));
        this.panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));

        this.buttons.get(0).addActionListener(new LoginService(this));
        this.buttons.get(1).addActionListener(new LoginService(this));


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }

    public Vector<JTextField> getFields(){
        return this.fields;
    }

    public JPasswordField getPwField(){
        return this.pwField;
    }

//    public static void main(String args[]) {
//
//    }
}
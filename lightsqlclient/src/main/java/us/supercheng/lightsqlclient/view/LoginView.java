package us.supercheng.lightsqlclient.view;

import us.supercheng.lightsqlclient.entity.PanelManager;
import us.supercheng.lightsqlclient.service.LoginService;
import us.supercheng.lightsqlclient.util.DBHelper;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/8/17.
 */
public class LoginView extends JPanel{
    private Vector<JLabel> labels;
    private Vector<JTextField> fields;
    private JPasswordField pwField;
    private Vector<Button> buttons;
    private Font font;
    private DBHelper dbHelper;
    private PanelManager panelMgmt;


    public LoginView(DBHelper inDBHelper, PanelManager inPanelMgmt){
        super(new GridLayout(6,2));
        this.dbHelper = inDBHelper;
        this.panelMgmt = inPanelMgmt;
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
        this.fields.get(0).setText("localhost");

        this.fields.add(new JTextField());
        this.fields.get(1).setText("3306");

        this.fields.add(new JTextField());
        this.fields.get(2).setText("hcheng");

        this.fields.add(new JTextField());
        this.fields.get(3).setText("root");


        this.buttons.add(new Button("Login"));
        this.buttons.add(new Button("Cancel"));

        this.add(this.labels.get(0));
        this.fields.get(0).setFont(this.font);
        this.add(this.fields.get(0));

        this.add(this.labels.get(1));
        this.fields.get(1).setFont(this.font);
        this.add(this.fields.get(1));

        this.add(this.labels.get(2));
        this.fields.get(2).setFont(this.font);
        this.add(this.fields.get(2));

        this.add(this.labels.get(3));
        this.fields.get(3).setFont(this.font);
        this.add(this.fields.get(3));

        this.add(this.labels.get(4));
        this.pwField.setFont(this.font);
        this.add(this.pwField);

        this.add(this.buttons.get(0));
        this.add(this.buttons.get(1));

        this.setPreferredSize(new Dimension(400,300));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));

        this.buttons.get(0).addActionListener(new LoginService(this, this.dbHelper, this.panelMgmt));
        this.buttons.get(1).addActionListener(new LoginService(this, this.dbHelper, this.panelMgmt));
    }

    public Vector<JTextField> getFields(){
        return this.fields;
    }

    public JPasswordField getPwField(){
        return this.pwField;
    }
}
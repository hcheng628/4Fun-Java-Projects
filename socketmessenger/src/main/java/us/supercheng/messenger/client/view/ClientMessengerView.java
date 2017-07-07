package us.supercheng.messenger.client.view;

import us.supercheng.messenger.client.entity.MessengerClient;
import us.supercheng.messenger.client.service.SendBtnActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by cl799honchen on 7/3/2017.
 */
public class ClientMessengerView extends JFrame {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    private JButton btnSend;
    private JTextField txtSend;
    private JScrollPane scroll;
    private MessengerClient messengerClient;

    private JTextPane jTxtPane;

    public ClientMessengerView(String inServerURL, String inServerPort){
        super("Bee Bee Messenger");
        try{
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());

        this.btnSend = new JButton("Send");
        this.txtSend = new JTextField(43);

        this.jTxtPane = new JTextPane();
        this.jTxtPane.setContentType("text/html");
        this.jTxtPane.setEditable(false);

        this.scroll = new JScrollPane (this.jTxtPane);
        this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scroll.setPreferredSize(new Dimension(280,450));

        this.messengerClient = new MessengerClient(inServerURL,inServerPort, this.jTxtPane);
        SendBtnActionListener sendBtnListener = new SendBtnActionListener(this.txtSend, this.jTxtPane, this.messengerClient);
        this.btnSend.addActionListener(sendBtnListener);

        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.gridwidth = 3; //
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        panel.add(this.scroll, layoutConstraints);

        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.ipady = 18;//reset to default
        layoutConstraints.anchor = GridBagConstraints.PAGE_END;
        layoutConstraints.weighty = 1.0;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.gridwidth = 2;//2 columns wide
        panel.add(this.txtSend, layoutConstraints);

        layoutConstraints.weighty = 1.0; //request any extra vertical space
        layoutConstraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        layoutConstraints.insets = new Insets(10,0,0,0); //top padding
        layoutConstraints.gridx = 2;//aligned with button 2
        layoutConstraints.gridwidth = 1;//2 columns wide
        layoutConstraints.gridy = 2; //third row
        panel.add(this.btnSend, layoutConstraints);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chat Box"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        } catch (Exception ignore){
            ignore.printStackTrace();
        }
    }

    public void stayOn(){
        Thread clientStayOnline = new Thread(messengerClient);
        clientStayOnline.start();
    }
}
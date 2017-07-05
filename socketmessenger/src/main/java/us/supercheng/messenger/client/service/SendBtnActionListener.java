package us.supercheng.messenger.client.service;

import us.supercheng.messenger.client.entity.MessengerClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cl799honchen on 7/5/2017.
 */
public class SendBtnActionListener implements ActionListener {
    private JTextField txtSend;
    private JTextArea paneChatTxt;
    private MessengerClient messageClient;

    public SendBtnActionListener(JTextField inTxtSend, JTextArea inPaneChatTxt, MessengerClient inMessageClient){
        this.txtSend = inTxtSend;
        this.paneChatTxt = inPaneChatTxt;
        this.messageClient = inMessageClient;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Sending:" + this.txtSend.getText().trim());
        try{
            this.messageClient.sendMessage(this.txtSend.getText().trim());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
        // this.paneChatTxt.setText(this.txtSend.getText().trim());
    }
}
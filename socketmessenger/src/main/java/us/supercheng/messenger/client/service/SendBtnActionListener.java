package us.supercheng.messenger.client.service;

import us.supercheng.messenger.client.entity.MessengerClient;
import us.supercheng.messenger.common.entity.BeeBeeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by cl799honchen on 7/5/2017.
 */
public class SendBtnActionListener implements ActionListener {
    private JTextField txtSend;
    private JTextPane paneChatTxt;
    private MessengerClient messengerClient;
    private BeeBeeMessage bbMsg;

    public SendBtnActionListener(JTextField inTxtSend, JTextPane inPaneChatTxt, MessengerClient inMessageClient){
        this.txtSend = inTxtSend;
        this.paneChatTxt = inPaneChatTxt;
        this.messengerClient = inMessageClient;
        this.bbMsg = new BeeBeeMessage();
    }

    public void actionPerformed(ActionEvent e) {
        try{
            this.bbMsg = new BeeBeeMessage(this.messengerClient.toString().substring(this.messengerClient.toString().indexOf('@') + 1),
                    this.messengerClient.toString().substring(this.messengerClient.toString().indexOf('@') + 1),
                    null, this.txtSend.getText().trim(),new Date());
            this.messengerClient.sendMessage(this.bbMsg.toString());
            this.txtSend.setText("");
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
}
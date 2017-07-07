package us.supercheng.messenger.client.entity;

import org.w3c.dom.html.HTMLDOMImplementation;
import us.supercheng.messenger.common.entity.BeeBeeMessage;

import javax.swing.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

/**
 * Created by hongyu on 6/25/17.
 */
public class MessengerClient implements Runnable{
    private Vector<String> messageList;
    private String serverURL;
    private int serverPort;
    private Socket clientSocket;
    private DataOutputStream dataGoingOutClient;
    private DataInputStream dataComingInClient;
    private BeeBeeMessage bbMsg;
    private JTextPane paneChatTxt;
    private HTMLEditorKit kit;

    public MessengerClient(String inServerURL, String inServerPort, JTextPane inPaneChatTxt) throws Exception{
        this.serverURL = inServerURL;
        this.serverPort = Integer.parseInt(inServerPort);
        this.clientSocket = new Socket(this.serverURL, this.serverPort);
        this.dataComingInClient = new DataInputStream(this.clientSocket.getInputStream());
        this.dataGoingOutClient = new DataOutputStream(this.clientSocket.getOutputStream());
        this.messageList = new Vector<String>();
        this.kit = new HTMLEditorKit();
        this.paneChatTxt = inPaneChatTxt;
        this.paneChatTxt.setEditorKit(this.kit);
        this.bbMsg = new BeeBeeMessage();

    }

    public void sendMessage(String inputStr) {
        try{
            this.dataGoingOutClient.writeUTF(inputStr);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String readMessage() throws Exception {
        return this.dataComingInClient.readUTF();
    }

    public void doDisconnect() throws Exception{
        this.clientSocket.close();
    }

    public void run() {
        while(true){
            try{
                String inMsg = this.dataComingInClient.readUTF();
                if(this.bbMsg.toBeeBeeMessage(inMsg) != null){
                    this.bbMsg = this.bbMsg.toBeeBeeMessage(inMsg);
                    this.paneChatTxt.setDocument((HTMLDocument) this.paneChatTxt.getDocument());
                    if(this.bbMsg.getSender().equals(this.toString().substring(this.toString().indexOf('@') + 1))){
                        this.kit.insertHTML((HTMLDocument) this.paneChatTxt.getDocument(), ((HTMLDocument) this.paneChatTxt.getDocument()).getLength(),
                                "<p align=\"right\">" + this.bbMsg.getMsg() + "</p>", 0, 0, null);
                    }else{
                        this.kit.insertHTML((HTMLDocument) this.paneChatTxt.getDocument(), ((HTMLDocument) this.paneChatTxt.getDocument()).getLength(),
                                "<p align=\"left\"><b>" + this.bbMsg.getSender() + ": </b>"  + this.bbMsg.getMsg() + "</p>", 0, 0, null);
                    }


                    this.messageList.add(inMsg);
                }else{
                    this.paneChatTxt.setText(this.paneChatTxt.getText() + inMsg + "\r\n");
                }
            }
            catch(Exception ignore){
                ignore.printStackTrace();
            }
        }
    }

    public Vector<String> getMessageList(){
        return this.messageList;
    }
}
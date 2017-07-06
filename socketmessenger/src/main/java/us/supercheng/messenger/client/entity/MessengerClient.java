package us.supercheng.messenger.client.entity;

import us.supercheng.messenger.common.entity.BeeBeeMessage;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
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

    private JTextArea paneChatTxt;

    public MessengerClient(String inServerURL, String inServerPort, JTextArea inPaneChatTxt) throws Exception{
        this.serverURL = inServerURL;
        this.serverPort = Integer.parseInt(inServerPort);
        this.clientSocket = new Socket(this.serverURL, this.serverPort);
        this.dataComingInClient = new DataInputStream(this.clientSocket.getInputStream());
        this.dataGoingOutClient = new DataOutputStream(this.clientSocket.getOutputStream());
        this.messageList = new Vector<String>();
        this.paneChatTxt = inPaneChatTxt;
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
                System.out.println(inMsg);
                this.paneChatTxt.setText(this.paneChatTxt.getText() + inMsg + "\r\n");
                this.messageList.add(inMsg);
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
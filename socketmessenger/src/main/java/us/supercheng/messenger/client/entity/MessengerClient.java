package us.supercheng.messenger.client.entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by hongyu on 6/25/17.
 */
public class MessengerClient {
    private String serverURL;
    private int serverPort;
    private Socket clientSocket;
    private DataOutputStream dataGoingOutClient;
    private DataInputStream dataComingInClient;

    public MessengerClient(String inServerURL, String inServerPort) throws Exception{
        this.serverURL = inServerURL;
        this.serverPort = Integer.parseInt(inServerPort);
        this.clientSocket = new Socket(this.serverURL, this.serverPort);
        this.dataComingInClient = new DataInputStream(this.clientSocket.getInputStream());
        this.dataGoingOutClient = new DataOutputStream(this.clientSocket.getOutputStream());
    }

    public void sendMessage(String inputStr) throws Exception{
        this.dataGoingOutClient.writeUTF(inputStr);
    }

    public String readMessage() throws Exception{
        return this.dataComingInClient.readUTF();
    }

    public void doDisconnect() throws Exception{
        this.clientSocket.close();
    }

    public void stayOnline() throws Exception{
        while(true){
            String inMsg = this.dataComingInClient.readUTF();
            System.out.println(inMsg);
        }
    }
}
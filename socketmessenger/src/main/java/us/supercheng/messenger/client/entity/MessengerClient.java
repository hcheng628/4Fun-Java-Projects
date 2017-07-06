package us.supercheng.messenger.client.entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by hongyu on 6/25/17.
 */
public class MessengerClient implements Runnable{
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

    public void sendMessage(String inputStr) {
        try{
            System.out.println( this + " : " + inputStr);
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
            System.out.println("Running");
            try{
                String inMsg = this.dataComingInClient.readUTF();
                System.out.println(inMsg);
            }
            catch(Exception ignore){
                ignore.printStackTrace();
            }
            System.out.println("Running-Complete");
        }
    }
}
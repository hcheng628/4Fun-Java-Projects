package us.supercheng.messenger.server.entity;

import us.supercheng.messenger.common.entity.BeeBeeMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

/**
 * Created by hongyu on 6/25/17.
 */
public class MessengerServer{
    private final String Server_Name = "Bee Bee Messenger Server Node 13";

    private Vector<Socket> clientSockets;
    private ServerSocket serverSocket;
    private int serverPort;

    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    private BeeBeeMessage bbMsg;

    public MessengerServer(int serverPort) throws Exception{
        this.serverPort = serverPort;
        this.serverSocket = new ServerSocket(serverPort);
        InetAddress myIP = InetAddress.getLocalHost();
        System.out.println("Server running on Host IP: " + myIP.getHostAddress() + " Host Name: " + myIP.getHostName() + " on Port: " + this.serverPort);
        this.clientSockets = new Vector<Socket>();
        this.bbMsg = new BeeBeeMessage();
    }

    public void keepOnServer(){
        Socket newClientSocket = null;
        try{
            while(true){
                newClientSocket = this.serverSocket.accept();
                this.clientSockets.add(newClientSocket);
                System.out.println("Server: Total Client Socket(s): " + this.clientSockets.size());
                dataOut = new DataOutputStream(newClientSocket.getOutputStream());
                this.bbMsg = new BeeBeeMessage(Server_Name, null, "Connected to Server @ localhost on Port: " + this.serverPort, new Date());
                dataOut.writeUTF(this.bbMsg.toString());
                ServerSocketAgent newServerSocketAgent = new ServerSocketAgent(newClientSocket, this.clientSockets);
                Thread newThread = new Thread(newServerSocketAgent);
                newThread.start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                this.serverSocket.close();
                newClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
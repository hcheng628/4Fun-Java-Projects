package us.supercheng.messenger.server.entity;

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
    private Vector<Socket> clientSockets;
    private ServerSocket serverSocket;
    private int serverPort;

    DataOutputStream dataOut;
    DataInputStream dataIn;

    public MessengerServer(int serverPort) throws Exception{
        this.serverPort = serverPort;
        this.serverSocket = new ServerSocket(serverPort);
        InetAddress myIP = InetAddress.getLocalHost();
        System.out.println("Server running on Host IP: " + myIP.getHostAddress() + " Host Name: " + myIP.getHostName() + " on Port: " + this.serverPort);
        this.clientSockets = new Vector<Socket>();
    }

    public void keepOnServer(){
        Socket newClientSocket = null;
        try{
            while(true){
                newClientSocket = this.serverSocket.accept();
                this.clientSockets.add(newClientSocket);
                System.out.println("Server: Total Client Socket(s): " + this.clientSockets.size());
                dataOut = new DataOutputStream(newClientSocket.getOutputStream());
                // dataIn = new DataInputStream(newClientSocket.getInputStream());
                dataOut.writeUTF("Connected to Server @ localhost on Port: " + this.serverPort);
                // System.out.println("Server: " + dataIn.readUTF());
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
package us.supercheng.messenger.server.entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

/**
 * Created by hongyu on 6/25/17.
 */
public class MessengerServer implements Runnable{
    private Vector<Socket> clientSockets;
    private ServerSocket serverSocket;
    private int serverPort;

    DataOutputStream dataOut;
    DataInputStream dataIn;

    public MessengerServer(int serverPort) throws Exception{
        this.serverPort = serverPort;
        this.serverSocket = new ServerSocket(serverPort);
        this.clientSockets = new Vector<Socket>();
    }

    public void run(){
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

    public void keepOnServer(){
        String newMsg = "";
        while(true){
            try{
                for(Socket eachClient : this.clientSockets) {
                    this.dataIn = new DataInputStream(eachClient.getInputStream());
                    if(this.dataIn != null){
                        String eachNewMsg = this.dataIn.readUTF();
                        newMsg = eachNewMsg;
                    }
                    if(newMsg.length() > 0){
                        System.out.println("Server Broadcast Msg: " + newMsg);

                        for(Socket eachOne : this.clientSockets){
                            this.dataOut = new DataOutputStream(eachOne.getOutputStream());
                            this.dataOut.writeUTF(newMsg);
                            newMsg = "";
                        }
                    }
                }


            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
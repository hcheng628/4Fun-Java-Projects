package us.supercheng.messenger.server.app;

import us.supercheng.messenger.server.entity.MessengerServer;

import java.util.Vector;

/**
 * Created by hongyu on 6/25/17.
 */
public class ServerApp {

    public static void main(String[]  args){
        try{
            MessengerServer server = new MessengerServer(8866);
            server.keepOnServer();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
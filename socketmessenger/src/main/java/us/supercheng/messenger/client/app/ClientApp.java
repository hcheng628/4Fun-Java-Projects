package us.supercheng.messenger.client.app;

import us.supercheng.messenger.client.entity.MessengerClient;

import java.util.Date;

/**
 * Created by hongyu on 6/25/17.
 */
public class ClientApp {
    private MessengerClient client;

    public static void main(String[]  args){
        try{
            MessengerClient client = new MessengerClient("localhost","8866");
            client.sendMessage("Join Request from Client @" + new Date().toString());
            client.stayOnline();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

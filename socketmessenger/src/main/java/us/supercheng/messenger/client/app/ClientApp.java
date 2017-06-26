package us.supercheng.messenger.client.app;

import us.supercheng.messenger.client.entity.MessengerClient;

/**
 * Created by hongyu on 6/25/17.
 */
public class ClientApp {
    private MessengerClient client;

    public static void main(String[]  args){
        try{
            MessengerClient client = new MessengerClient("localhost","8866");
            client.sendMessage("Join Request");
            client.stayOnline();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

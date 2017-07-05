package us.supercheng.messenger.client.app;

import us.supercheng.messenger.client.entity.MessengerClient;
import us.supercheng.messenger.client.view.ClientMessengerView;

import java.util.Date;

/**
 * Created by hongyu on 6/25/17.
 */
public class ClientApp {
    private MessengerClient client;

    public static void main(String[]  args){
        try{
            ClientMessengerView clientView = new ClientMessengerView();
            clientView.setVisible(true);
            clientView.setResizable(false);
            clientView.stayOn();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

package us.supercheng.messenger.common.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Date;

/**
 * Created by cl799honchen on 7/6/2017.
 */

public class BeeBeeMessage {
    private String sender;
    private String receiver;
    private String msg;
    private Date currentDatetime;
    private ObjectMapper jsonMapper;

    // This default constructor is needed for jackson2-json-binding
    public BeeBeeMessage(){
        this.jsonMapper = new ObjectMapper();
        this.sender = null;
        this.receiver = null;
        this.msg = null;
        this.currentDatetime = null;
    }
    // This default constructor is needed for jackson2-json-binding


    public BeeBeeMessage(String inSender, String inReveiver, String inMsg, Date inCurrentDateTime){
        this.jsonMapper = new ObjectMapper();
        this.sender = inSender;
        this.receiver = inReveiver;
        this.msg = inMsg;
        this.currentDatetime = inCurrentDateTime;

    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMsg() {
        return msg;
    }

    public Date getCurrentDatetime() {
        return currentDatetime;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCurrentDatetime(Date currentDatetime) {
        this.currentDatetime = currentDatetime;
    }

    @Override
    public String toString() {
        String jsonInString = null;
        try{
            // jsonMapper.writeValue(new File("C://bbmsg.json"),this);
            jsonInString = this.jsonMapper.writeValueAsString(this);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return jsonInString;
    }

    public BeeBeeMessage toBeeBeeMessage(String inBeeBeeMessage){
        BeeBeeMessage javaBeeBeeObj = null;
        try{
            javaBeeBeeObj = this.jsonMapper.readValue(inBeeBeeMessage, BeeBeeMessage.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return javaBeeBeeObj;
    }
}
package us.supercheng.messenger.common.entity.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.messenger.common.entity.BeeBeeMessage;

import java.util.Date;


/**
 * Created by cl799honchen on 7/6/2017.
 */
public class BeeBeeMessageTest {

    BeeBeeMessage bbMsg;

    @Before
    public void warmUp(){
        this.bbMsg = new BeeBeeMessage("1", "Cheng","Leo", "Come Back", new Date());
    }

    @Test
    public void toString_Test(){
        System.out.println(this.bbMsg.toString());
    }

    @Test
    public void toBeeBeeMessage_Test(){
        System.out.println(this.bbMsg.toBeeBeeMessage(this.bbMsg.toString()).getMsg());
    }
}
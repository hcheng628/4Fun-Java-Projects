package us.supercheng.safe1pass.util.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.safe1pass.util.RestAPIHelper;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class UtilRestAPIHelper_Test {

    private RestAPIHelper restAPIHelper;

    @Before
    public void warmUp() {
        this.restAPIHelper = new RestAPIHelper();
    }

    @Test
    public void rest_POST_Test() throws Exception {
        String response = this.restAPIHelper.rest_POST("http://localhost:8080/app/v1.1/api/login",
                "username=a&password=a",
                "application/x-www-form-urlencoded");
        System.out.println("Response rest_POST_Test: " + response);
    }

    @Test
    public void rest_PUT_Test() throws Exception {
        String response = this.restAPIHelper.rest_PUT("http://localhost:8080/app/v1.1/api/post",
                "username=a&postname=aaa.txt&postcontent=Success  Haha",
                "application/x-www-form-urlencoded");
        System.out.println("Response rest_PUT_Test: " + response);
    }

    @Test
    public void rest_GET_Test() throws Exception {
        String response = this.restAPIHelper.rest_GET("http://localhost:8080/app/v1.1/api/listposts",
                "username=a");
        System.out.println("Response rest_GET_Test: " + response);
    }
}
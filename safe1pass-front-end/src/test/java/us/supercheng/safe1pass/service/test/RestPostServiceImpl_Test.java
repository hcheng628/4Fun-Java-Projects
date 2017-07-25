package us.supercheng.safe1pass.service.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.safe1pass.service.RestPostServiceImpl;
import java.util.Properties;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class RestPostServiceImpl_Test {

    private RestPostServiceImpl restPostService;
    private Properties prop;

    @Before
    public void warmUp() {
        this.prop = new Properties();
        prop.put("rest.api.endpoint.baseurl","http://localhost:8080/app/v1.1/api/");
        prop.put("rest.api.endpoint.method.login","login");
        prop.put("rest.api.endpoint.method.register","register");
        prop.put("rest.api.endpoint.method.listposts","listposts");
        prop.put("rest.api.endpoint.method.post","post");

        this.restPostService = new RestPostServiceImpl(this.prop);
    }

    @Test
    public void getListOfPostFiles_Test() throws Exception {
        for(String each : this.restPostService.getListOfPostFiles("a")) {
            System.out.println("Each: " + each);
        }
    }

    @Test
    public void getPostContent_Test() throws Exception {
        System.out.println(this.restPostService.getPostContent("Post1.txt","a"));
    }

    @Test
    public void savePost_Test() {
        this.restPostService.savePost("a","TestCheng.txt","Having a Test");
    }
}
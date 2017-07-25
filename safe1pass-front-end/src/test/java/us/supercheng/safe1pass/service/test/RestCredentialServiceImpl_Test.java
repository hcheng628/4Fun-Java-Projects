package us.supercheng.safe1pass.service.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.safe1pass.service.RestCredentialServiceImpl;
import us.supercheng.safe1pass.service.RestPostServiceImpl;

import java.util.Properties;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class RestCredentialServiceImpl_Test {

    private RestCredentialServiceImpl restCredentialService;
    private Properties prop;

    @Before
    public void warmUp() {
        this.prop = new Properties();
        prop.put("rest.api.endpoint.baseurl","http://localhost:8080/app/v1.1/api/");
        prop.put("rest.api.endpoint.method.login","login");
        prop.put("rest.api.endpoint.method.finduser","finduser");
        prop.put("rest.api.endpoint.method.register","register");
        prop.put("rest.api.endpoint.method.listposts","listposts");
        prop.put("rest.api.endpoint.method.post","post");

        this.restCredentialService = new RestCredentialServiceImpl(this.prop);
    }

    @Test
    public void login_Test() throws Exception {
       System.out.println( this.restCredentialService.login("a","b"));
    }

    @Test
    public void findUsername_Test() throws Exception {
        System.out.println(this.restCredentialService.findUsername("illy"));
    }

    @Test
    public void createNewCredential_Test() throws Exception {
        char [] pass = {'1','2','3'};
        System.out.println(this.restCredentialService.createNewCredential("Test",pass, pass));
    }
}
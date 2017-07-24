package us.supercheng.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import us.supercheng.app.entity.Greeting;
import us.supercheng.app.service.FileCredentialServiceImpl;
import us.supercheng.app.service.FilePostServiceImpl;
import us.supercheng.app.service.PropServiceImpl;
import java.util.List;

/**
 * Created by cl799honchen on 7/23/2017.
 */

@Controller
public class APIControllerImpl implements IAPIController {

    private FileCredentialServiceImpl fileCredentialService;
    private FilePostServiceImpl filePostService;



    public APIControllerImpl () {
        try {
            PropServiceImpl propService = new PropServiceImpl();
            this.fileCredentialService = new FileCredentialServiceImpl(propService);
            this.filePostService = new FilePostServiceImpl(propService);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Boolean login(String username, String password) {
        System.out.println("login --- username: " + username + " PW: " + password + " " + this.getClass().getSimpleName());
        return this.fileCredentialService.login(username,password);
    }

    @Override
    public Boolean register(String username, String password) {
        System.out.println("register --- username: " + username + " PW: " + password + " " + this.getClass().getSimpleName());
        if(username != null && password != null ) {
            if(this.fileCredentialService.createNewCredential(username, password)) {
                this.filePostService.createNewUserPostRepo(username);
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException("Username and Postname are Required");
        }
    }

    @Override
    public List<String> listPosts(String username) {
        System.out.println("login --- username: " + username + " " + this.getClass().getSimpleName());
        if(username == null || username.trim().length()<1){
            throw new RuntimeException("Username is Required");
        }
        return this.filePostService.getListOfPostFiles(username);
    }

    @Override
    public Boolean createPost(String username, String postname) {
        System.out.println("createPost --- username: " + username + " postname:" + postname + " " + this.getClass().getSimpleName());
        return this.savePost(username,postname,"");
    }

    @Override
    public String readPost(String username, String postname) {
        System.out.println("readPost --- username: " + username + " postname:" + postname + " " + this.getClass().getSimpleName());
        return this.filePostService.getPostContent( username, postname);
    }

    @Override
    public Boolean savePost(String username, String postname, String postcontent) {
        System.out.println("savePost --- username: " + username + " postname: " + postname + " postcontent: " + postcontent + " " + this.getClass().getSimpleName());
        if (username != null && postname != null){
            return this.filePostService.savePost(username, postname, postcontent);
        } else {
            throw new RuntimeException("Username and Postname are Required");
        }
    }

    @Override
    public Greeting test(String name) {
        return new Greeting(666, String.format("Hello, %s!", name));
    }
}
package us.supercheng.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.supercheng.app.entity.Greeting;
import us.supercheng.app.service.FileCredentialServiceImpl;
import us.supercheng.app.service.FilePostService;
import java.util.List;

/**
 * Created by cl799honchen on 7/23/2017.
 */

@Controller
public class APIControllerImpl implements IAPIController {

    private FileCredentialServiceImpl fileCredentialService;
    private FilePostService filePostService;


    public APIControllerImpl () {
        this.fileCredentialService = new FileCredentialServiceImpl();
        this.filePostService = new FilePostService();
        System.out.println("Cheng APIControllerImpl: " + this.getClass().getResourceAsStream("/Password/Pass.properties"));

    }

    @Override
    public Boolean login(String username, String password) {
        return this.fileCredentialService.login(username,password);
    }

    @Override
    public Boolean register(String username, String password) {
        return this.fileCredentialService.createNewCredential(username, password);
    }

    @Override
    public List<String> listPosts(String username) {
        return this.filePostService.getListOfPostFiles(username);
    }

    @Override
    public Boolean createPost(String username, String postName) {
        // Havent Implemented yet
        return null;
    }

    @Override
    public String readPost(String username, String postName) {
        return this.filePostService.getPostContent( username + "/" + postName);
    }

    @Override
    public Boolean savePost(String username, String postName, String postContent) {
        return this.filePostService.savePost(username + "/" + postName, postContent);
    }

    @Override
    public Greeting test(String name) {
        return new Greeting(666, String.format("Hello, %s!", name));
    }
}
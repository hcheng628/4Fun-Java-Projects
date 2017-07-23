package us.supercheng.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.supercheng.app.entity.Greeting;
import java.util.List;

/**
 * Created by cl799honchen on 7/23/2017.
 */

@Controller
public class APIControllerImpl implements IAPIController {
    @Override
    public Boolean login(String username, String password) {
        return null;
    }

    @Override
    public Boolean register(String username, String password) {
        return null;
    }

    @Override
    public List<String> listPosts(String username) {
        return null;
    }

    @Override
    public Boolean createPost(String username, String postName) {
        return null;
    }

    @Override
    public String readPost(String username, String postName) {
        return "Sample Post File Content";
    }

    @Override
    public Boolean savePost(String username, String postName, String postContent) {
        return null;
    }

    @Override
    public Greeting test(String name) {
        return new Greeting(666, String.format("Hello, %s!", name));
    }
}
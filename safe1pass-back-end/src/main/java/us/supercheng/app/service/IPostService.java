package us.supercheng.app.service;

import java.util.List;

/**
 * Created by cl799honchen on 7/19/2017.
 */

public interface IPostService {
    List<String> getListOfPostFiles(String username);
    String getPostContent(String postFilename);
    Boolean savePost(String postFilePath, String postContent);
    void createNewUserPostRepo(String username);
}

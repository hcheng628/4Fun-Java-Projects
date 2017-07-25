package us.supercheng.safe1pass.service;

import java.util.List;

/**
 * Created by cl799honchen on 7/19/2017.
 */
public interface IPostService {
    List<String> getListOfPostFiles(String username);
    String getPostContent(String postFilename, String username);
    void savePost(String username, String postname, String postContent);
}
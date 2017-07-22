package us.supercheng.safe1pass.dao;

/**
 * Created by cl799honchen on 7/18/2017.
 */
public interface IDao {
    void createUserRepo (String username);
    void createUserPost(String userPostName);
    void saveUserPost(String userPostContent);
}
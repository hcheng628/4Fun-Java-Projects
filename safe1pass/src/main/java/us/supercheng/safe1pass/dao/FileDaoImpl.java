package us.supercheng.safe1pass.dao;

import java.net.URISyntaxException;

/**
 * Created by cl799honchen on 7/18/2017.
 */
public class FileDaoImpl implements IDao {

    private final String LOCAL_FILE_REPO_PATH = this.getClass().getResource("/Repo/" ).toURI().toString();

    private String currentUserRepoRelativePath;
    private String currentUserRepoAbsPath;

    public FileDaoImpl(String currentUserRepoRelativePath) throws URISyntaxException {
        // System.out.println(LOCAL_FILE_REPO_PATH);
        this.currentUserRepoRelativePath = currentUserRepoRelativePath;
        this.currentUserRepoAbsPath = LOCAL_FILE_REPO_PATH + this.currentUserRepoRelativePath;
        System.out.println(currentUserRepoAbsPath);
    }

    public String getcurrentUserRepoRelativePath() {
        return currentUserRepoRelativePath;
    }

    public void setcurrentUserRepoRelativePath(String currentUserRepoRelativePath) {
        this.currentUserRepoRelativePath = currentUserRepoRelativePath;
    }

    public String getLOCAL_FILE_REPO_PATH() {
        return LOCAL_FILE_REPO_PATH;
    }

    @Override

    public void createUserRepo(String username) {

    }

    @Override
    public void createUserPost(String userPostName) {

    }

    @Override
    public void saveUserPost(String userPostContent) {

    }
}
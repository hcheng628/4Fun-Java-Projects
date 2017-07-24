package us.supercheng.app.service;

import us.supercheng.app.util.FileHelperImpl;
import us.supercheng.app.util.IFileHelper;
import java.util.List;
import java.util.Properties;

/**
 * Created by cl799honchen on 7/19/2017.
 */

public class FilePostServiceImpl implements IPostService {

    private final String FILE_REPO_RELATIVE_PATH = "/Repo/";

    private FileHelperImpl fileHelper;
    private String appLocalPath;

    public FilePostServiceImpl(PropServiceImpl propServiceImpl) {
        this.fileHelper = new FileHelperImpl();
        this.appLocalPath = propServiceImpl.getAppLocalRootPath();
    }

    @Override
    public List<String> getListOfPostFiles(String username) {
        try {
            return this.fileHelper.getFileList(this.appLocalPath + FILE_REPO_RELATIVE_PATH + username + "/");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getPostContent(String username, String postFilename) {
        try {
            // System.out.println("Another Checking: " + FILE_REPO_RELATIVE_PATH +  postFilename);
            return this.fileHelper.readTxtFile(this.appLocalPath + FILE_REPO_RELATIVE_PATH + username + "/" +  postFilename);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Boolean savePost(String username, String postFilename, String postContent) {
        try {
            this.fileHelper.saveTxtFile(this.appLocalPath + FILE_REPO_RELATIVE_PATH + username + "/" +  postFilename, postContent);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void createNewUserPostRepo(String username) {
        System.out.println("createNewUserPostRepo: " + username);
        this.fileHelper.createDirectory(this.appLocalPath + FILE_REPO_RELATIVE_PATH + username + "/");
    }
}
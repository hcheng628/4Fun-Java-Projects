package us.supercheng.app.service;

import us.supercheng.app.util.FileHelperImpl;
import us.supercheng.app.util.IFileHelper;
import java.util.List;

/**
 * Created by cl799honchen on 7/19/2017.
 */

public class FilePostService implements IPostService {

    private final String FILE_REPO_RELATIVE_PATH = "/Repo/";

    private IFileHelper fileHelper;

    public FilePostService () {
        this.fileHelper = new FileHelperImpl();
    }

    @Override
    public List<String> getListOfPostFiles(String username) {
        try {
            return this.fileHelper.getFileList(this.getClass().getResource(FILE_REPO_RELATIVE_PATH + username + "/").toURI().getPath());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getPostContent(String postFilename) {
        try {
            // System.out.println("Another Checking: " + FILE_REPO_RELATIVE_PATH +  postFilename);
            return this.fileHelper.readTxtFile(this.getClass().getResource(FILE_REPO_RELATIVE_PATH +  postFilename).toURI().getPath());
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Boolean savePost(String postFilePath, String postContent) {
        try {
            this.fileHelper.saveTxtFile(this.getClass().getResource(FILE_REPO_RELATIVE_PATH + postFilePath).toURI().getPath(), postContent);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void createNewUserPostRepo(String username) {
        try {
            this.fileHelper.createDirectory((this.getClass().getResource(FILE_REPO_RELATIVE_PATH).toURI().getPath() + username + "/").substring(1));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
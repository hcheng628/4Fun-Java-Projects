package us.supercheng.safe1pass.service;

import us.supercheng.safe1pass.util.FileHelperImpl;
import us.supercheng.safe1pass.util.IFileHelper;

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
            System.out.println("Another Checking: " + FILE_REPO_RELATIVE_PATH +  postFilename);
            return this.fileHelper.readTxtFile(this.getClass().getResource(FILE_REPO_RELATIVE_PATH +  postFilename).toURI().getPath());
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

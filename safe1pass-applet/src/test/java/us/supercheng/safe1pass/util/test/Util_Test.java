package us.supercheng.safe1pass.util.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.safe1pass.util.FileHelperImpl;
import java.util.List;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class Util_Test {
    final String readTxtFile_Filename = "readTxtFile_Test.txt";
    final String saveTxtFile_Filename = "saveTxtFile_Test.txt";
    final String createDirectory_FullPath = "C:\\Users\\cl799honchen\\Desktop" + "\\ChengCheng\\";
    final String getPostFileList_FullPath = createDirectory_FullPath;

    private FileHelperImpl fileHelper;

    @Before
    public void warmUp() {
        this.fileHelper = new FileHelperImpl();
    }

    @Test
    public void readTxtFile_Test() throws Exception {
        String readTxtFileFullPath = this.getClass().getResource("/" + readTxtFile_Filename).toURI().getPath();
        System.out.println(this.fileHelper.readTxtFile(readTxtFileFullPath));
    }

    @Test
    public void saveTxtFile_Test() throws Exception {
        this.fileHelper.saveTxtFile(this.getClass().getResource("/" + saveTxtFile_Filename).toURI().getPath(),"Think");
    }

    @Test
    public void createDirectory_Test() throws Exception {
        this.fileHelper.createDirectory(createDirectory_FullPath);
    }

    @Test
    public void getPostFileList_Test() throws Exception {
        List<String> fileList = this.fileHelper.getFileList(this.getClass().getResource("/Repo/Cheng/").toURI().getPath().toString());
        for(String eachFileName : fileList){
            System.out.println(eachFileName);
        }
    }

    @Test
    public void checkPropFileKey_Test() throws Exception {
        String key = "a";
        String temp = this.fileHelper.getPropFileValueFromKey(key,this.getClass().getResource("/Password/Pass.properties").toURI().getPath().toString());
        System.out.println(temp.equals(""));
    }
}
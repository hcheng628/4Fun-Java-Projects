package us.supercheng.safe1pass.util;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public interface IFileHelper {
    void readTxtFile(String fullFilePath);
    void saveTxtFile(String fullFilePath, String txtContent);
    void createDirectory(String fullDirectoryPath);
}

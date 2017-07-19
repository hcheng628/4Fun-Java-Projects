package us.supercheng.safe1pass.util;

import java.io.InputStream;
import java.util.List;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public interface IFileHelper {
    void readTxtFile(String fullFilePath);
    void saveTxtFile(String fullFilePath, String txtContent);
    void createDirectory(String fullDirectoryPath);
    String getStringFromInputStream(InputStream is);
    List<String> getFileList(String fullDirPath);
    String getPropFileValueFromKey(String keyName, String propFileFullPath);
    void savePropFile(String key, String value, String propFileFullPath);
}

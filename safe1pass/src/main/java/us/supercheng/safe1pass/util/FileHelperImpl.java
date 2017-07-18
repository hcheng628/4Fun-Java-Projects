package us.supercheng.safe1pass.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class FileHelperImpl implements IFileHelper {

    @Override
    public void readTxtFile(String fullFilePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(fullFilePath))){
            String line = "";
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void saveTxtFile(String fullFilePath, String txtContent) {
        System.out.println(fullFilePath);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fullFilePath))){
            bw.write(txtContent);
            System.out.println("Done");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getStringFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        return sb.toString();
    }

    @Override
    public void createDirectory(String fullDirectoryPath) {
        System.out.println(fullDirectoryPath);
        Path path = Paths.get(fullDirectoryPath);
        if(!Files.exists(path)){
            try{
                Files.createDirectories(path);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getPostFileList(String fullDirPath) {
        System.out.println(fullDirPath);
        List<String> fileNameList = new ArrayList<String>();
        File [] files = new File(fullDirPath).listFiles();
        for(File file : files){
            if(file.isFile()){
                fileNameList.add(file.getName());
            }
        }
        return fileNameList;
    }

    @Override
    public String getPropFileValueFromKey(String keyName, String propFileFullPath) {
        Properties prop = new Properties();
        try(InputStream is = new FileInputStream(propFileFullPath)){
            prop.load(is);
            return (String) prop.get(keyName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void savePropFile(String key, String value, String propFileFullPath) {
        Properties prop = new Properties();
        try(InputStream is = new FileInputStream(propFileFullPath)){
            prop.load(is);
            prop.setProperty(key,value);
            prop.store(new FileOutputStream(propFileFullPath),null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
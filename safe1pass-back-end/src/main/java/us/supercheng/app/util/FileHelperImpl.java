package us.supercheng.app.util;

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
    public String readTxtFile(String fullFilePath) {
        String returnStr = "";
        try(BufferedReader br = new BufferedReader(new FileReader(fullFilePath))){
            String line = "";
            while((line = br.readLine()) != null) {
                returnStr += line;
            }
            return returnStr;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void saveTxtFile(String fullFilePath, String txtContent) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fullFilePath))){
            bw.write(txtContent);
            System.out.println(fullFilePath + " Done!");
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
        // System.out.println(fullDirectoryPath);
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
    public List<String> getFileList(String fullDirPath) {
        // System.out.println(fullDirPath);
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
    public String getPropFileValueFromKey(String keyName, InputStream in) {
        Properties prop = new Properties();
        try{
            prop.load(in);
            return (String) prop.get(keyName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void savePropFile(String key, String value, InputStream in) {
        Properties prop = new Properties();
        try{
            prop.load(in);
            prop.setProperty(key,value);
            //prop.store(new FileOutputStream(propFileFullPath),null);
            prop.store(new FileOutputStream(this.getClass().getResource()),null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String inputStreamToString (InputStream in) throws Exception{
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String read;
        while((read=br.readLine()) != null) {
            sb.append(read);
        }
        br.close();
        return sb.toString();
    }
}
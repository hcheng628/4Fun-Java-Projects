package us.supercheng.safe1pass.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

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
    public void createDirectory(String fullDirectoryPath) {

    }
}

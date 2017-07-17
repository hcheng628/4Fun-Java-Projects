package us.supercheng.safe1pass.util.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.safe1pass.util.FileHelperImpl;

/**
 * Created by cl799honchen on 7/17/2017.
 */
public class Util_Test {
    final String readTxtFile_Filename = "readTxtFile_Test.txt";
    final String saveTxtFile_Filename = "saveTxtFile_Test.txt";


    private FileHelperImpl fileHelper;

    @Before
    public void warmUp() {
        this.fileHelper = new FileHelperImpl();
    }

    @Test
    public void readTxtFile_Test() throws Exception {
        System.out.println(this.getClass().getResource("/" ).toURI().getPath());

        // System.out.println(this.getClass().getResource("/" + readTxtFile_Filename).toURI().getPath());
        // this.fileHelper.readTxtFile(this.getClass().getResource("/" + readTxtFile_Filename).toURI().getPath());
    }

    @Test
    public void saveTxtFile_Test() throws Exception {
        System.out.println(this.getClass().getResource("/" + saveTxtFile_Filename).toURI().getPath());
        // this.fileHelper.saveTxtFile(this.getClass().getResource("/" + saveTxtFile_Filename).toURI().getPath(),"Think");
    }
}

package us.supercheng.camera.opencv.openvc.up.test;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.Date;

/**
 * Created by cl799honchen on 7/30/2017.
 */
public class OpenCV_Test {

    /* This is to ensure OpenCV has been correctly configured */
    @Test
    public void openCV_Up_Test() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m = new Mat(50,100, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: \n" + m );
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("Mat Data:\n" + m.dump());
    }

    @Test
    public void test() {
        java.util.Date javaDate = null;
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());

        System.out.println("Mat Data:\n" + sqlDate);
    }
}
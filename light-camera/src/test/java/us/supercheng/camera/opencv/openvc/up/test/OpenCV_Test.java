package us.supercheng.camera.opencv.openvc.up.test;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

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
        String CameraPanel_DETECTION_ON = "Detection On";
        String CameraPanel_DETECTION_OFF = "Detection Off";

        System.out.println("DetectionOFF: " + CameraPanel_DETECTION_OFF.indexOf(CameraPanel_DETECTION_OFF.substring(10)));
        System.out.println("DetectionON: " + CameraPanel_DETECTION_ON.indexOf(CameraPanel_DETECTION_OFF.substring(10)));

    }
}
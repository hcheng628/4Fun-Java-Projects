package us.supercheng.camera.opencv.app;

import org.opencv.core.Core;
import us.supercheng.camera.opencv.view.CameraPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Created by cl799honchen on 7/30/2017.
 */
public class App {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        JFrame mainFrame = new JFrame();
        CameraPanel cameraView = new CameraPanel();

        mainFrame.add(cameraView);
        mainFrame.setContentPane(cameraView);
        mainFrame.setSize(new Dimension(500,500));
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}

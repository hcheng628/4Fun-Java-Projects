package us.supercheng.camera.opencv.us.supercheng.camera.opencv.service.view;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import us.supercheng.camera.opencv.view.CameraPanel;
import us.supercheng.camera.opencv.view.IViewKeyword;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cl799honchen on 7/30/2017.
 */
public class CameraViewService implements Runnable, ActionListener {

    CameraPanel selfView;

    public CameraViewService(CameraPanel cameraPanel) {
        this.selfView = cameraPanel;
    }

    public void run() {
        if (this.selfView.isCameraFlag()) {
            this.selfView.setCamera(new VideoCapture(0));
        }
        while (true) {
            this.selfView.getCamera().read(this.selfView.getMat());
            if (!this.selfView.getMat().empty()) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this.selfView);
                mainFrame.setSize(this.selfView.getMat().width() + 40, this.selfView.getMat().height() + 110);
                this.selfView.setImg(toBufferedImage(this.selfView.getMat()));
                this.selfView.repaint();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof javax.swing.JMenuItem) {
            JMenuItem eventMenuItem = (JMenuItem) e.getSource();
            if (eventMenuItem.getText().equals(IViewKeyword.CameraPanel_SAVE_PIC)) {
                try {
                    ImageIO.write(this.selfView.getImg(), "jpg",
                            new File("Pic-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpg"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (eventMenuItem.getText().indexOf(IViewKeyword.CameraPanel_CAMERA) >= 0) {
                this.switchCamera((Integer.parseInt(eventMenuItem.getText().replaceAll("\\D+", "")) - 1));
            } else if (eventMenuItem.getText().indexOf(IViewKeyword.CameraPanel_DETECTION_OFF.substring(0,10))>=0) {
               if (eventMenuItem.getText().indexOf(IViewKeyword.CameraPanel_DETECTION_OFF.substring(10))>=0) {
                    System.out.println("User Turn Off " + eventMenuItem.getText().indexOf(IViewKeyword.CameraPanel_DETECTION_OFF.substring(0,10)));
                    eventMenuItem.setText(IViewKeyword.CameraPanel_DETECTION_OFF);
                    this.selfView.setDetectionFlag(true);
               } else {
                   System.out.println("User Turn On " + eventMenuItem.getText().indexOf(IViewKeyword.CameraPanel_DETECTION_OFF.substring(0,10)));
                   eventMenuItem.setText(IViewKeyword.CameraPanel_DETECTION_ON);
                   this.selfView.setDetectionFlag(false);
               }
            }
        }
    }

    private void switchCamera(int inCameraIndex) {
        this.selfView.getCamera().release();
        this.selfView.setCamera(new VideoCapture(inCameraIndex));
    }

    private BufferedImage toBufferedImage(Mat matBGR) {
        int width = matBGR.width();
        int height = matBGR.height();
        int channels = matBGR.channels();
        byte[] source = new byte[width * height * channels];
        matBGR.get(0, 0, source);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] target = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(source, 0, target, 0, source.length);
        return image;
    }
}
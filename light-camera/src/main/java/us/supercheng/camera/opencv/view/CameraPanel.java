package us.supercheng.camera.opencv.view;

import org.opencv.videoio.VideoCapture;
import us.supercheng.camera.opencv.us.supercheng.camera.opencv.service.view.CameraViewService;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by cl799honchen on 7/30/2017.
 */
public class CameraPanel extends JPanel {
    private JMenuBar menuBar;
    private JMenu menu;
    private Vector<JMenuItem> menuItems;

    private BufferedImage img;
    private VideoCapture camera;

    private boolean cameraFlag;
    private int cameraIndex;
    private CameraViewService cameraViewService;

    public CameraPanel () {
        this.cameraViewService = new CameraViewService(this);

        this.setLayout(new BorderLayout());
        this.menuBar = new JMenuBar();
        this.menu = new JMenu(IViewKeyword.CameraPanel_SELECT_CAMERA);
        this.menuBar.add(this.menu);
        this.menuItems = new Vector<JMenuItem>();

        this.cameraIndex = 0;
        this.camera = new VideoCapture(cameraIndex);
        if(this.camera.isOpened()){
            this.cameraFlag = true;
            JMenuItem savePic = new JMenuItem(IViewKeyword.CameraPanel_SAVE_PIC);
            savePic.addActionListener(this.cameraViewService);
            this.menuItems.add(savePic);
        }

        while(this.camera.isOpened()) {
            JMenuItem eachCam = new JMenuItem(IViewKeyword.CameraPanel_CAMERA + (cameraIndex +1));
            eachCam.addActionListener(this.cameraViewService);
            this.menuItems.add(eachCam);
            this.camera.release();
            this.camera = new VideoCapture(++cameraIndex);
        }

        for (JMenuItem eachItem : this.menuItems) {
            this.menu.add(eachItem);
        }
        this.add(this.menuBar, BorderLayout.NORTH);

        Thread newThread = new Thread(this.cameraViewService);
        newThread.start();
    }

    public VideoCapture getCamera() {
        return this.camera;
    }

    public void setCamera(VideoCapture inVideoCapture) {
        this.camera = inVideoCapture;
    }

    public BufferedImage getImg() {
        return this.img;
    }

    public void setImg(BufferedImage inBufferedImage) {
        this.img = inBufferedImage;
    }

    public int getCameraIndex() {
        return cameraIndex;
    }

    public void setCameraIndex(int cameraIndex) {
        this.cameraIndex = cameraIndex;
    }

    public boolean isCameraFlag() {
        return cameraFlag;
    }

    public void setCameraFlag(boolean cameraFlag) {
        this.cameraFlag = cameraFlag;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (img==null) return;
        g.drawImage(img,10,40,img.getWidth(),img.getHeight(), null);
        // g.setColor(Color.GREEN);
//        for(Rect rect : faceDetections.toArray()){
//            g.drawRect(rect.x+10, rect.y+40, rect.width, rect.height);
//        }
    }
}
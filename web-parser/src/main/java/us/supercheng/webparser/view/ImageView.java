package us.supercheng.webparser.view;

import us.supercheng.webparser.service.view.HyperLinkViewService;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cl799honchen on 8/1/2017.
 */
public class ImageView extends JPanel {
    private List<JLabel> imageUrlLabels;
    private HyperLinkViewService imageViewService;

    public ImageView (List<String> imgURLList) throws Exception {
        this.imageViewService = new HyperLinkViewService();
        this.imageUrlLabels = new ArrayList<JLabel>();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for (String eachURL : imgURLList ){
            JLabel eachImgURL = new JLabel(eachURL);
            eachImgURL.addMouseListener(imageViewService);
            this.imageUrlLabels.add(eachImgURL);
            this.add(eachImgURL);
        }
    }
}
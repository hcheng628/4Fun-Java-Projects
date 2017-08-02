package us.supercheng.webparser.view;

import us.supercheng.webparser.service.view.HyperLinkViewService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cl799honchen on 8/1/2017.
 */
public class LinkView extends JPanel {
    private List<JLabel> linkUrlLabels;
    private HyperLinkViewService linkViewService;

    public LinkView (List<String> imgURLList) throws Exception {
        this.linkViewService = new HyperLinkViewService();
        this.linkUrlLabels = new ArrayList<JLabel>();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for (String eachURL : imgURLList ){
            JLabel eachLinkURL = new JLabel(eachURL);
            eachLinkURL.addMouseListener(linkViewService);
            this.linkUrlLabels.add(eachLinkURL);
            this.add(eachLinkURL);
        }
    }
}

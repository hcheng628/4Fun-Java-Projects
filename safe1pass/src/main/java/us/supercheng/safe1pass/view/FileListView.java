package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.FileListViewService;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */
public class FileListView extends JPanel {

    private Vector<JLabel> fileListViewLabs;
    private Vector<JRadioButton> fileListViewRadio;
    private Vector<JButton> fileListViewBtns;
    private JPanel fileListViewPanel;
    private FileListViewService fileListViewService;

    public FileListView (JPanel mainPanel) {
        this.fileListViewService = new FileListViewService(mainPanel);
        this.fileListViewLabs = new Vector<JLabel>();
        this.fileListViewLabs.add(new JLabel(IViewKeyword.FILELIST_VIEW_LIST_OF_FILES));

        this.fileListViewPanel = new JPanel(new FlowLayout());

        this.fileListViewBtns = new Vector<JButton>();
        this.fileListViewBtns.add(new JButton(IViewKeyword.REGISTER_VIEW_CREATE));
        this.fileListViewBtns.get(0).addActionListener(this.fileListViewService);
        this.fileListViewBtns.add(new JButton(IViewKeyword.FILELIST_VIEW_OPEN));
        this.fileListViewBtns.get(1).addActionListener(this.fileListViewService);
        this.fileListViewBtns.add(new JButton(IViewKeyword.FILELIST_VIEW_REFRESH));
        this.fileListViewBtns.get(2).addActionListener(this.fileListViewService);

        this.fileListViewRadio = new Vector<JRadioButton>();
        this.fileListViewRadio.add(new JRadioButton());
        this.fileListViewRadio.add(new JRadioButton());

        this.fileListViewLabs.add(new JLabel("Fake 1"));
        this.fileListViewLabs.add(new JLabel("Fake 2"));


        this.fileListViewPanel.add(this.fileListViewLabs.get(0));

        for(int i=0;i<this.fileListViewRadio.size(); i++) {
            this.fileListViewPanel.add(this.fileListViewRadio.get(i));
            this.fileListViewPanel.add(this.fileListViewLabs.get(i+1));
        }

        this.fileListViewPanel.add(this.fileListViewBtns.get(0));
        this.fileListViewPanel.add(this.fileListViewBtns.get(1));

        this.add(this.fileListViewPanel);

    }
}

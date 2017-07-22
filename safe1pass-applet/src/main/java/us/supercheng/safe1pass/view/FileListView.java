package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.FilePostService;
import us.supercheng.safe1pass.service.IPostService;
import us.supercheng.safe1pass.service.view.FileListViewService;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */

public class FileListView extends JPanel {

    private String username;
    private Vector<JLabel> fileListViewLabs;
    private Vector<JRadioButton> fileListViewRadio;
    private ButtonGroup fileListViewRadioGroup;
    private Vector<JButton> fileListViewBtns;
    private JPanel fileListViewPanel;
    private FileListViewService fileListViewService;
    private IPostService postService;

    public FileListView (Applet mainPanel, String username) {
        this.username = username;
        this.postService = new FilePostService();
        this.fileListViewService = new FileListViewService(this, mainPanel);
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
        this.fileListViewRadioGroup = new ButtonGroup();

        for(int i=0; i<this.postService.getListOfPostFiles(username).size();i++) {
            this.fileListViewRadio.add(new JRadioButton(this.postService.getListOfPostFiles(username).get(i)));
            fileListViewRadioGroup.add(this.fileListViewRadio.get(i));
        }


        this.fileListViewPanel.add(this.fileListViewLabs.get(0));
        for(int i=0;i<this.fileListViewRadio.size(); i++) {
            this.fileListViewPanel.add(this.fileListViewRadio.get(i));
        }

        this.fileListViewPanel.add(this.fileListViewBtns.get(0));
        this.fileListViewPanel.add(this.fileListViewBtns.get(1));

        this.add(this.fileListViewPanel);
    }

    public String getSelectPostFile(){
        for(JRadioButton each : this.fileListViewRadio){
            if(each.isSelected()){
                return each.getText();
            }
        }
        return "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public void refreshFileListView () {
//        this.fileListViewRadio = new Vector<JRadioButton>();
//        this.fileListViewRadioGroup = new ButtonGroup();
//        for(int i=0; i<this.postService.getListOfPostFiles(username).size();i++) {
//            this.fileListViewRadio.add(new JRadioButton(this.postService.getListOfPostFiles(username).get(i)));
//            fileListViewRadioGroup.add(this.fileListViewRadio.get(i));
//        }
//    }
}
package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.FilePostServiceImpl;
import us.supercheng.safe1pass.service.IPostService;
import us.supercheng.safe1pass.service.RestPostServiceImpl;
import us.supercheng.safe1pass.service.view.FileListViewService;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */

public class FileListView extends JPanel {

    private String username;
    private JTextField newPostFileTxt;
    private Vector<JLabel> fileListViewLabs;
    private Vector<JRadioButton> fileListViewRadio;
    private ButtonGroup fileListViewRadioGroup;
    private Vector<JButton> fileListViewBtns;
    private JPanel fileListViewPanel;
    private FileListViewService fileListViewService;
    private RestPostServiceImpl restPostService;

    public FileListView (JPanel mainPanel, String username, Properties inAppProp) {
        this.username = username;
        this.restPostService = new RestPostServiceImpl(inAppProp);
        this.fileListViewService = new FileListViewService(this, mainPanel, inAppProp);
        this.fileListViewLabs = new Vector<JLabel>();
        this.fileListViewLabs.add(new JLabel(IViewKeyword.FILELIST_VIEW_LIST_OF_FILES));

        this.fileListViewPanel = new JPanel(new BorderLayout());
        this.newPostFileTxt = new JTextField("",13);
        this.fileListViewBtns = new Vector<JButton>();
        this.fileListViewBtns.add(new JButton(IViewKeyword.REGISTER_VIEW_CREATE));
        this.fileListViewBtns.get(0).addActionListener(this.fileListViewService);
        this.fileListViewBtns.add(new JButton(IViewKeyword.FILELIST_VIEW_OPEN));
        this.fileListViewBtns.get(1).addActionListener(this.fileListViewService);
        this.fileListViewBtns.add(new JButton(IViewKeyword.FILELIST_VIEW_REFRESH));

        // Dont need this at this point ;-)
        this.fileListViewBtns.get(2).addActionListener(this.fileListViewService);

        this.fileListViewRadio = new Vector<JRadioButton>();
        this.fileListViewRadioGroup = new ButtonGroup();


        JPanel raidoBtnPanel =  null;

        if (username != null && username.length() > 0) {
            for(int i=0; i<this.restPostService.getListOfPostFiles(username).size();i++) {
                this.fileListViewRadio.add(new JRadioButton(this.restPostService.getListOfPostFiles(username).get(i)));
                fileListViewRadioGroup.add(this.fileListViewRadio.get(i));
            }
            raidoBtnPanel = new JPanel();
        }

        this.fileListViewPanel.add(this.fileListViewLabs.get(0));
        for(int i=0;i<this.fileListViewRadio.size(); i++) {
            raidoBtnPanel.add(this.fileListViewRadio.get(i));
        }

        if (raidoBtnPanel != null) {
            this.fileListViewPanel.add(raidoBtnPanel, BorderLayout.NORTH);
        }

        this.fileListViewPanel.add(this.newPostFileTxt, BorderLayout.LINE_START);
        this.fileListViewPanel.add(this.fileListViewBtns.get(0), BorderLayout.CENTER);
        this.fileListViewPanel.add(this.fileListViewBtns.get(1), BorderLayout.LINE_END);

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

    public JTextField getNewPostFileTxt() {
        return newPostFileTxt;
    }

    public void setNewPostFileTxt(JTextField newPostFileTxt) {
        this.newPostFileTxt = newPostFileTxt;
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
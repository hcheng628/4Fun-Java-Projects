package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.service.FilePostService;
import us.supercheng.safe1pass.service.IPostService;
import us.supercheng.safe1pass.util.FileHelperImpl;
import us.supercheng.safe1pass.util.IFileHelper;
import us.supercheng.safe1pass.view.EditorView;
import us.supercheng.safe1pass.view.FileListView;
import us.supercheng.safe1pass.view.IViewKeyword;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */
public class FileListViewService implements ActionListener {

    private JPanel mainPanel;
    private FileListView selfView;
    private IPostService postService;

    public FileListViewService (FileListView selfView, JPanel mainPanel) {
        this.selfView = selfView;
        this.mainPanel = mainPanel;
        this.postService = new FilePostService();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.REGISTER_VIEW_CREATE)) {
                System.out.println(IViewKeyword.REGISTER_VIEW_CREATE + "File");
            } else if(eventBtn.getText().equals(IViewKeyword.FILELIST_VIEW_OPEN)) {
                // System.out.println("Checking Content:" + postContent);
                this.mainPanel.add(new EditorView(this.mainPanel, this.selfView.getUsername() + "/" + this.selfView.getSelectPostFile(),
                        this.postService.getPostContent(this.selfView.getUsername() + "/" + this.selfView.getSelectPostFile())), IServiceKeyword.EDITOR_VIEW);
                ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, IServiceKeyword.EDITOR_VIEW);
            } else if(eventBtn.getText().equals(IViewKeyword.FILELIST_VIEW_REFRESH)) {
                System.out.println(IViewKeyword.FILELIST_VIEW_REFRESH + "File");
            } else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
        }
    }
}
package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.service.FilePostServiceImpl;
import us.supercheng.safe1pass.service.IPostService;
import us.supercheng.safe1pass.view.EditorView;
import us.supercheng.safe1pass.view.IViewKeyword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */

public class EditorViewService implements ActionListener {

    private JPanel mainPanel;
    private EditorView selfView;
    private IPostService postService;

    public EditorViewService (EditorView editorView, JPanel mainPanel) {
        this.postService = new FilePostServiceImpl();
        this.selfView = editorView;
        this.mainPanel = mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() instanceof javax.swing.JButton) {
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText() == IViewKeyword.EDITOR_VIEW_SAVE){
                // Save it
                this.postService.savePost(this.selfView.getPostFileFullPath(),"", selfView.getPostContent());
            } else if (eventBtn.getText() == IViewKeyword.EDITOR_VIEW_SAVE_N_CLOSE) {
                // Save and Close meaning back to FileListService Page
                // System.out.println("Saving: " + selfView.getPostContent());
                this.postService.savePost(this.selfView.getPostFileFullPath(), "", selfView.getTextAreaContent());
                ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, IServiceKeyword.FILELIST_VIEW);
            } else {
                System.out.println("No Event Triggered @actionPerformed " + this.getClass().getSimpleName());
            }
        }
    }
}
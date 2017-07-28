package us.supercheng.safe1pass.service.view;

import us.supercheng.safe1pass.service.RestCredentialServiceImpl;
import us.supercheng.safe1pass.service.RestPostServiceImpl;
import us.supercheng.safe1pass.view.EditorView;
import us.supercheng.safe1pass.view.FileListView;
import us.supercheng.safe1pass.view.IViewKeyword;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by hongyu on 7/16/17.
 */

public class EditorViewService implements ActionListener {

    private JPanel mainPanel;
    private EditorView selfView;
    private RestPostServiceImpl restPostService;
    private RestCredentialServiceImpl restCredentialService;

    public EditorViewService (EditorView editorView, JPanel mainPanel, Properties inAppProp) {
        this.selfView = editorView;
        this.mainPanel = mainPanel;
        this.restPostService = new RestPostServiceImpl(inAppProp);
        this.restCredentialService = new RestCredentialServiceImpl(inAppProp);
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() instanceof javax.swing.JButton) {
            // System.out.println("EditorViewService: " + this.selfView.getPostFilename());
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText() == IViewKeyword.EDITOR_VIEW_SAVE){
                // Save it
                this.restPostService.savePost(this.selfView.getPostFileFullPath().substring(0,this.selfView.getPostFileFullPath().length()-1),
                        this.selfView.getPostFilename(), selfView.getTextAreaContent());
            } else if (eventBtn.getText() == IViewKeyword.EDITOR_VIEW_SAVE_N_CLOSE) {
                // Save and Close meaning back to FileListService Page
                // System.out.println("Saving: " + selfView.getPostContent());
                this.restPostService.savePost(this.selfView.getPostFileFullPath().substring(0,this.selfView.getPostFileFullPath().length()-1),
                        this.selfView.getPostFilename(), selfView.getTextAreaContent());
                this.mainPanel.add(new FileListView(this.mainPanel, this.selfView.getPostFileFullPath().substring(0,this.selfView.getPostFileFullPath().length()-1), this.restCredentialService.getRestAPIProp()), IServiceKeyword.FILELIST_VIEW);

                ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, IServiceKeyword.FILELIST_VIEW);
            } else {
                System.out.println("No Event Triggered @actionPerformed " + this.getClass().getSimpleName());
            }
        }
    }
}
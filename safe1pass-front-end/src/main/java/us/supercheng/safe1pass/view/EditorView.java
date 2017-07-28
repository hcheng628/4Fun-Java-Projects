package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.view.EditorViewService;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */

public class EditorView extends JPanel {

    private String postFileFullPath;
    private String postContent;
    private String postFilename;
    private JTextArea editorViewTxtArea;
    private Vector<JButton> editorViewBtns;
    private  JPanel editorViewPanel;
    private EditorViewService editorViewService;

    public EditorView(JPanel mainPanel, String postFileFullPath, String postContent, Properties inAppProp, String postFilename) {
        // System.out.println("EditorView init PostFilename Checking: " + postFilename);

        this.postFileFullPath = postFileFullPath;
        this.postContent = postContent;
        this.postFilename = postFilename;

        this.editorViewService = new EditorViewService(this, mainPanel, inAppProp);
        this.editorViewPanel = new JPanel(new FlowLayout());
        this.editorViewTxtArea = new JTextArea(30,15);
        this.editorViewTxtArea.setText(postContent);

        this.editorViewBtns = new Vector<JButton>();
        this.editorViewBtns.add(new JButton(IViewKeyword.EDITOR_VIEW_SAVE));
        this.editorViewBtns.get(0).addActionListener(this.editorViewService);
        this.editorViewBtns.add(new JButton(IViewKeyword.EDITOR_VIEW_SAVE_N_CLOSE));
        this.editorViewBtns.get(1).addActionListener(this.editorViewService);

        this.editorViewPanel.add(this.editorViewTxtArea);
        this.editorViewPanel.add(this.editorViewBtns.get(0));
        this.editorViewPanel.add(this.editorViewBtns.get(1));

        this.add(this.editorViewPanel);
    }

    public String getPostFilename() {
        return postFilename;
    }

    public void setPostFilename(String postFilename) {
        this.postFilename = postFilename;
    }

    public String getPostFileFullPath() {
        return postFileFullPath;
    }

    public String getPostContent() {

        return postContent;
    }


    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostFileFullPath(String postFileFullPath) {
        this.postFileFullPath = postFileFullPath;
    }

    public String getTextAreaContent() {
        return this.editorViewTxtArea.getText();
    }
}
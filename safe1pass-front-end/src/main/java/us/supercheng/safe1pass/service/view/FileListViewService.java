package us.supercheng.safe1pass.service.view;

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
public class FileListViewService implements ActionListener {

    private JPanel mainPanel;
    private FileListView selfView;
    private RestPostServiceImpl restPostService;

    public FileListViewService (FileListView selfView, JPanel mainPanel, Properties inAppProp) {
        this.selfView = selfView;
        this.mainPanel = mainPanel;
        this.restPostService = new RestPostServiceImpl(inAppProp);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            JButton eventBtn = (JButton) e.getSource();
            System.out.println("FileListViewService: getNewPostFileTxt: " + this.selfView.getNewPostFileTxt().getText() + ".txt");
            System.out.println("FileListViewService: getSelectPostFile: " + this.selfView.getSelectPostFile());

            if(eventBtn.getText().equals(IViewKeyword.REGISTER_VIEW_CREATE)) {

                if (this.selfView.getNewPostFileTxt().getText().trim().length() > 0) {
                    System.out.println("IViewKeyword.REGISTER_VIEW_CREATE");
                    EditorView refreshEditorView = new EditorView(
                            this.mainPanel,
                            this.selfView.getUsername() + "/" + this.selfView.getSelectPostFile(),
                            "",
                            this.restPostService.getRestAPIProp(),
                            this.selfView.getNewPostFileTxt().getText() + ".txt");
                    // this.mainPanel.add(refreshEditorView), IServiceKeyword.EDITOR_VIEW)
                    this.mainPanel.add(refreshEditorView, IServiceKeyword.EDITOR_VIEW);
                    ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, IServiceKeyword.EDITOR_VIEW);
                } else {
                    System.out.println("Please enter a valid post name");
                }

            } else if(eventBtn.getText().equals(IViewKeyword.FILELIST_VIEW_OPEN)) {
                System.out.println("IViewKeyword.FILELIST_VIEW_OPEN");

                if(!this.selfView.getSelectPostFile().equals("")) {
                    EditorView refreshEditorView = new EditorView(
                            this.mainPanel,
                            this.selfView.getUsername() + "/",
                            this.restPostService.getPostContent(this.selfView.getUsername() + "/" + this.selfView.getSelectPostFile(),""),
                            this.restPostService.getRestAPIProp(),
                            this.selfView.getSelectPostFile());

                    // this.mainPanel.add(refreshEditorView), IServiceKeyword.EDITOR_VIEW)
                    this.mainPanel.add(refreshEditorView, IServiceKeyword.EDITOR_VIEW);
                    ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, IServiceKeyword.EDITOR_VIEW);
                }

            } else if(eventBtn.getText().equals(IViewKeyword.FILELIST_VIEW_REFRESH)) {
                System.out.println(IViewKeyword.FILELIST_VIEW_REFRESH + "File");
            } else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
        }
    }
}
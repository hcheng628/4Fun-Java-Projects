package us.supercheng.safe1pass.service;

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

    public FileListViewService (JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof javax.swing.JButton){
            JButton eventBtn = (JButton) e.getSource();
            if(eventBtn.getText().equals(IViewKeyword.REGISTER_VIEW_CREATE)) {
                System.out.println(IViewKeyword.REGISTER_VIEW_CREATE + "File");
            } else if(eventBtn.getText().equals(IViewKeyword.FILELIST_VIEW_OPEN)) {
                ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, IServiceKeyword.EDITOR_VIEW);
            } else if(eventBtn.getText().equals(IViewKeyword.FILELIST_VIEW_REFRESH)) {
                System.out.println(IViewKeyword.FILELIST_VIEW_REFRESH + "File");
            } else {
                System.out.println("actionPerformed @" + this.getClass().getSimpleName() + ": No Action Performed.");
            }
        }
    }
}
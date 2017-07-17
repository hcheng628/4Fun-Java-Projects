package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.EditorViewService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */

public class EditorView extends JPanel {

    private JTextArea editorViewTxtArea;
    private Vector<JButton> editorViewBtns;
    private  JPanel editorViewPanel;
    private EditorViewService editorViewService;

    public EditorView() {
        this.editorViewService = new EditorViewService();
        this.editorViewPanel = new JPanel(new FlowLayout());
        this.editorViewTxtArea = new JTextArea(30,15);

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
}

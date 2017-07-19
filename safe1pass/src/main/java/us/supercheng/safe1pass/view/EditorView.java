package us.supercheng.safe1pass.view;

import us.supercheng.safe1pass.service.view.EditorViewService;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */

public class EditorView extends JPanel {

    private JTextArea editorViewTxtArea;
    private Vector<JButton> editorViewBtns;
    private  JPanel editorViewPanel;
    private EditorViewService editorViewService;

    public EditorView(JPanel mainPanel) {
        this.editorViewService = new EditorViewService(mainPanel);
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

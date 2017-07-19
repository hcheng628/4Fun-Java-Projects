package us.supercheng.safe1pass.service.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */

public class EditorViewService implements ActionListener {

    private JPanel mainPanel;

    public EditorViewService (JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
    }
}

package us.supercheng.blockbreaker.app;

import us.supercheng.blockbreaker.entity.BlockPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Created by hongyu on 6/24/17.
 */
public class App {
    public static void main(String [] args){
        JFrame frame = new JFrame("Block Breaker");
        BlockPanel panel = new BlockPanel();
        panel.setLayout(new FlowLayout());
        panel.setFocusable(true);
        panel.addKeyListener(panel);

        frame.add(panel);
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

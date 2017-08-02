package us.supercheng.webparser.service.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.net.URI;
import java.util.Map;

/**
 * Created by cl799honchen on 8/2/2017.
 */
public class HyperLinkViewService implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof javax.swing.JLabel) {
            JLabel urlLabel = (JLabel) e.getSource();
            // System.out.println(urlLabel.getText());
            try {
                Desktop.getDesktop().browse(new URI(urlLabel.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof javax.swing.JLabel) {
            JLabel urlLabel = (JLabel) e.getSource();
            Font font = urlLabel.getFont();
            Map attr = font.getAttributes();
            attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            urlLabel.setFont(font.deriveFont(attr));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof javax.swing.JLabel) {
            JLabel urlLabel = (JLabel) e.getSource();
            Font font = urlLabel.getFont();
            Map attr = font.getAttributes();
            attr.put(TextAttribute.UNDERLINE, -1);
            urlLabel.setFont(font.deriveFont(attr));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}

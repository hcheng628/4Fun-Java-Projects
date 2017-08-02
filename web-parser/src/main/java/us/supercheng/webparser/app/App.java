package us.supercheng.webparser.app;

import us.supercheng.webparser.service.HTMLParserServiceImpl;
import us.supercheng.webparser.view.ImageView;
import us.supercheng.webparser.view.LinkView;
import us.supercheng.webparser.view.WordCountView;
import javax.swing.*;
import java.awt.*;

/**
 * Created by cl799honchen on 8/1/2017.
 */

public class App {
    public static void main (String[]  args ) {
        String webUrl = "http://mycrif.crifnet.com";
        JFrame mainFrame = new JFrame("Web-Parser");
        JTabbedPane tabbedPane = new JTabbedPane();
        try {
            HTMLParserServiceImpl htmlParserService = new HTMLParserServiceImpl(webUrl);
            tabbedPane.add("Image", new ImageView(htmlParserService.getAllImages()));
            tabbedPane.add("Link",  new LinkView(htmlParserService.getAllLinks()));
            tabbedPane.add("Word Count", new WordCountView(htmlParserService.getAllWordCount()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollFrame = new JScrollPane(tabbedPane);
        scrollFrame.setPreferredSize(new Dimension(330,660));

        mainFrame.add(scrollFrame);
        mainFrame.setContentPane(scrollFrame);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(true);
    }
}
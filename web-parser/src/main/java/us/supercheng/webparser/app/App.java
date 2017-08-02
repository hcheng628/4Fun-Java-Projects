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
    private final static String Application_Name = "Web-Parser";
    private String webUrl;
    private JFrame mainFrame;
    private JTabbedPane tabbedPane;

    public App(String webUrl) {
        this.webUrl = webUrl;
        this.mainFrame = new JFrame(Application_Name);
        this.tabbedPane = new JTabbedPane();

        try {
            HTMLParserServiceImpl htmlParserService = new HTMLParserServiceImpl(this.webUrl);
            this.tabbedPane.add("Image", new ImageView(htmlParserService.getAllImages()));
            this.tabbedPane.add("Link",  new LinkView(htmlParserService.getAllLinks()));
            this.tabbedPane.add("Word Count", new WordCountView(htmlParserService.getAllWordCount()));
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

    public static void main (String[]  args ) {
        JFrame frame = new JFrame(Application_Name);
        String webUrl = JOptionPane.showInputDialog(frame,"Enter Web URL: ", "http://mycrif.crifnet.com");
        App app = new App(webUrl);
    }
}
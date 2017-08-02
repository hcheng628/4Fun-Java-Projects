package us.supercheng.webparser.view;

import us.supercheng.webparser.entity.WordCount;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cl799honchen on 8/1/2017.
 */
public class WordCountView extends JPanel {
    private List<JLabel> wordCountLabels;

    public  WordCountView (List<WordCount> inWordCountList) {
        this.wordCountLabels = new ArrayList<JLabel>();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for (WordCount eachWordCount : inWordCountList ){
            JLabel eachImgURL = new JLabel("Count: " + eachWordCount.getCount() + "      " + eachWordCount.getWord());
            this.wordCountLabels.add(eachImgURL);
            this.add(eachImgURL);
        }
    }
}
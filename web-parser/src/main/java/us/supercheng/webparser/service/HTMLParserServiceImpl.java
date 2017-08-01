package us.supercheng.webparser.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.supercheng.webparser.entity.WordCount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cl799honchen on 8/1/2017.
 */
public class HTMLParserServiceImpl implements IHTMLParserService {

    private Document webDocument;
    private String webURL;

    public HTMLParserServiceImpl (String webURL) throws Exception{
        this.webURL = webURL;
        this.webDocument = Jsoup.connect(this.webURL).get();
    }

    @Override
    public List<String> getAllLinks() {
        List<String> returnStr = new ArrayList<>();
        Elements elements = this.webDocument.select("a[href]");
        for (Element element : elements) {
            for (Attribute eachAttr : element.attributes()) {
                if (eachAttr.getKey().equals("href")){
                    if(eachAttr.getValue().indexOf("http")>=0) {
                        // System.out.println(eachAttr.getValue().replaceAll(" ","%20"));
                        returnStr.add(eachAttr.getValue().replaceAll(" ","%20"));
                    }else if (eachAttr.getValue().indexOf('/') == 0) {
                        // System.out.println(this.webURL + eachAttr.getValue().replaceAll(" ","%20"));
                        returnStr.add(this.webURL + eachAttr.getValue().replaceAll(" ","%20"));
                    }
                }
            }
        }
        return returnStr;
    }

    @Override
    public List<String> getAllImages() {
        List<String> returnStr = new ArrayList<>();
        Elements elements = this.webDocument.select("img[src]");
        for (Element element : elements) {
            for (Attribute eachAttr : element.attributes()) {
                if (eachAttr.getKey().equals("src")){
                    if(eachAttr.getValue().indexOf("http")>=0) {
                        // System.out.println(eachAttr.getValue().replaceAll(" ","%20"));
                        // returnStr.add(eachAttr.getValue().replaceAll(" ","%20"));
                    }else if (eachAttr.getValue().indexOf('/') == 0) {
                        // System.out.println(this.webURL + eachAttr.getValue().replaceAll(" ","%20"));
                        returnStr.add(this.webURL + eachAttr.getValue().replaceAll(" ","%20"));
                    }
                }
            }
        }
        return returnStr;
    }

    @Override
    public List<WordCount> getAllWordCount() {
        List<WordCount> returnWordCount = new ArrayList<WordCount>();

        String webText = this.webDocument.text().trim();
        String[] wordList = webText.split(" ");
        WordCount firstWordCount =  new WordCount();
        firstWordCount.setWord(wordList[0]);
        firstWordCount.setCount(1);
        returnWordCount.add(firstWordCount);

        for (int i = 1; i<wordList.length;i++) {
            boolean foundFlag = false;
            for (int j = 0; j < returnWordCount.size(); j++) {
                if (wordList[i].toLowerCase().equals(returnWordCount.get(j).getWord().toLowerCase())) {
                    returnWordCount.get(j).setCount(returnWordCount.get(j).getCount() + 1);
                    foundFlag = true;
                    break;
                }
            }
            if( !foundFlag){
                WordCount newWord = new WordCount();
                newWord.setWord(wordList[i]);
                newWord.setCount(1);
                returnWordCount.add(newWord);
            }
        }

        Collections.sort(returnWordCount,new Comparator<WordCount>(){
            @Override
            public int compare(final WordCount a, final WordCount b) {
                if (a.getCount() < b.getCount()) {
                    return 1;
                } else if ((a.getCount() == b.getCount())) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return returnWordCount;
    }
}

package us.supercheng.webparser.service.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.webparser.entity.WordCount;
import us.supercheng.webparser.service.HTMLParserServiceImpl;

/**
 * Created by cl799honchen on 7/30/2017.
 */
public class HTMLParserServiceImpl_Test {

    private HTMLParserServiceImpl htmlParserService;

    @Before
    public void warmUp() throws Exception {
        this.htmlParserService = new HTMLParserServiceImpl("https://www.google.com/");
    }

    @Test
    public void getAllLinks_Test() {
        int count = 1;
        for (String eachLink : this.htmlParserService.getAllLinks()) {
            System.out.println(count++ + " " + eachLink);
        }
    }

    @Test
    public void getAllImages_Test() {
        int count = 1;
        for (String eachLink : this.htmlParserService.getAllImages()) {
            System.out.println(count++ + " " + eachLink);
        }
    }

    @Test
    public void getAllWordCount_Test() {
        int count = 1;
        for (WordCount eachWordCount : this.htmlParserService.getAllWordCount()) {
            System.out.println(count++ + ": " + eachWordCount);
        }
    }
}
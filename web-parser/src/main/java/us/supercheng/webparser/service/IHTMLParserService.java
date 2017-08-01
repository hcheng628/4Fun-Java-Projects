package us.supercheng.webparser.service;

import us.supercheng.webparser.entity.WordCount;

import java.util.List;

/**
 * Created by cl799honchen on 8/1/2017.
 */
public interface IHTMLParserService {
    List<String> getAllLinks();
    List<String> getAllImages();
    List<WordCount> getAllWordCount();
}

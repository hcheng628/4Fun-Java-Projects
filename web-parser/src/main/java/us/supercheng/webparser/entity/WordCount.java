package us.supercheng.webparser.entity;

/**
 * Created by cl799honchen on 8/1/2017.
 */
public class WordCount {
    private String word;
    private Integer count;

    public WordCount() {
        this.word = null;
        this.count = 0;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WordCount{" +
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }
}

package io.github.henryw;

import io.github.henryw.utils.Printer;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Class.class.getName());

    public static void main(String[] args) {
        testTfIdf();
        testOneSentence();
    }

    private static void testOneSentence() {
        String corpus = "他来到了网易杭研大厦";
        List<Term> terms = ToAnalysis.parse(corpus).getTerms();
        LOGGER.info(terms.toString());
    }

    private static void testTfIdf() {
        String[] corpus = {
                "这  是  ; ； 中文这个字",
                "测试 a中文字"};
        //    segmentation
        double[][] tfidf = new TFIDFExtractor().fit_transform(corpus);
        Printer.print2dArray(tfidf);
    }


}

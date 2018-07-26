package io.github.henryw;

import io.github.henryw.utils.Printer;
import io.github.henryw.utils.ResourceReader;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Class.class.getName());

    public static void main(String[] args) {
        String[] corpus = {
                "这  是  ; ； 中文这个字",
                "测试 a中文字"};
        //    segmentation
        double[][] tfidf = new TFIDFExtractor().fit_transform(corpus);
        Printer.print2dArray(tfidf);
    }

}

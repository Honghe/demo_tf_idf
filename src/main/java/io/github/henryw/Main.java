package io.github.henryw;

import io.github.henryw.utils.Printer;

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

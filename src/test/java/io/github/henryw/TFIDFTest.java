package io.github.henryw;

import io.github.henryw.utils.Printer;
import org.junit.Test;

public class TFIDFTest {

    @Test
    public void fit_transform() {
        int[][] counts = {{3, 0, 1},
                {2, 0, 0},
                {3, 0, 0},
                {4, 0, 0},
                {3, 2, 0},
                {3, 0, 2}};
        TFIDFCalculator tfidfCalculator = new TFIDFCalculator();
        double[][] tfidf = tfidfCalculator.fit_transform(counts);
        new Printer().print2dArray(tfidf);
    }
}
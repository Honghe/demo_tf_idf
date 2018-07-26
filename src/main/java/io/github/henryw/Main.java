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
        String[] corpus = {"这  是  ; ； 中文",
                "测 试 中  a 文字"};
        //    segmentation
        Set<String> stopwords = new ResourceReader().getStopwords("my_stopwords.txt");
        LOGGER.info(Arrays.toString(stopwords.toArray()));

        List<List<String>> documents = new ArrayList<List<String>>();
        for (int i = 0; i < corpus.length; i++) {
//            documents.add(ToAnalysis.parse(corpus[i]).getTerms());
            List<Term> terms = ToAnalysis.parse(corpus[i]).getTerms();
            List<String> termsSeg = new ArrayList<String>();
            for (Iterator<Term> it = terms.iterator(); it.hasNext(); ) {
                String termName = it.next().getName().trim();
                // stop word filter
                if (!stopwords.contains(termName) && termName.length()>0) {
                    termsSeg.add(termName);
                }
            }
            documents.add(termsSeg);
        }

        //    Count
        CountVectorizer countVectorizer = new CountVectorizer();
        List<List<Integer>> vectors = countVectorizer.fit_transform(documents);
        List<String> feature_names = countVectorizer.get_feature_names();

        Printer.print2dArrayInt(vectors);
        LOGGER.info(feature_names.toString());
        //    if-idf
        double[][] tfidf = new TFIDFCalculator().fit_transform(vectors);
        Printer.print2dArray(tfidf);
    }
}

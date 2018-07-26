package io.github.henryw;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TFIDFCalculator {
    private static Logger LOGGER = Logger.getLogger(Class.class.getName());

    public double[][] euclideanNorm(double[][] vector) {
        double[][] tfIdfNorm = new double[vector.length][vector[0].length];
        for (int i = 0; i < vector.length; i++) {
            double[] row = vector[i];
            double rowSqrtSumOfSquares = 0;
            for (int j = 0; j < row.length; j++) {
                rowSqrtSumOfSquares += Math.pow(row[j],2);
            }
            rowSqrtSumOfSquares = Math.sqrt(rowSqrtSumOfSquares);
            for (int j = 0; j < row.length; j++) {
                tfIdfNorm[i][j] = row[j]/rowSqrtSumOfSquares;
            }
        }
        return tfIdfNorm;
    }

    /**
     * >>> tf = [[3, 0, 1],
     * ...           [2, 0, 0],
     * ...           [3, 0, 0],
     * ...           [4, 0, 0],
     * ...           [3, 2, 0],
     * ...           [3, 0, 2]]
     * ...
     * >>> tfidf = transformer.fit_transform(tf)
     * >>> tfidf
     * <6x3 sparse matrix of type '<... 'numpy.float64'>'
     * with 9 stored elements in Compressed Sparse ... format>
     * <p>
     * >>> tfidf.toarray()
     * array([[ 0.81940995,  0.        ,  0.57320793],
     * [ 1.        ,  0.        ,  0.        ],
     * [ 1.        ,  0.        ,  0.        ],
     * [ 1.        ,  0.        ,  0.        ],
     * [ 0.47330339,  0.88089948,  0.        ],
     * [ 0.58149261,  0.        ,  0.81355169]])
     *
     * @return
     */
    public double[][] fit_transform(int[][] tf) {
        double[][] tfIdf = new double[tf.length][tf[0].length];
        int numDocument = tf.length;
        int[] df = df(tf);
        double[] idf = idf(numDocument, df);
        for (int i = 0; i < tf.length; i++) {
            int[] row = tf[i];
            for (int j = 0; j < row.length; j++) {
                tfIdf[i][j] = tf[i][j] * idf[j];
            }
        }
        return euclideanNorm(tfIdf);
    }

    private double[] idf(int numDocument, int[] df) {
        double[] idf = new double[df.length];
        for (int i = 0; i < df.length; i++) {
//            df[i] + 1 for smooth_idf
            // use smooth_idf=True, adds “1” to the numerator and denominator
            // as if an extra document was seen containing every term in the collection exactly once,
            // which prevents zero divisions
            idf[i] = Math.log((double)(numDocument + 1)/(df[i] + 1)) + 1;
        }
        return idf;
    }

    private int[] df(int[][] counts) {
        int[] df = new int[counts[0].length];
        for (int i = 0; i < counts.length; i++) {
            int[] row = counts[i];
            for (int j = 0; j <row.length; j++) {
                if (row[j] > 0) {
                    df[j] += 1;
                }
            }
        }
        LOGGER.info("df: " + Arrays.toString(df));
        return df;
    }

    public double[][] fit_transform(List<List<Integer>> vectors) {
        int[][] vectorsArray = new int[vectors.size()][vectors.get(0).size()];
        for (int i = 0; i < vectors.size(); i++) {
            List<Integer> row = vectors.get(i);
            for (int j = 0; j < row.size(); j++) {
                vectorsArray[i][j] = row.get(j);
            }
        }
        return fit_transform(vectorsArray);
    }
}

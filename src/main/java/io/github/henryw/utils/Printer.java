package io.github.henryw.utils;

import java.util.List;
import java.util.logging.Logger;

public class Printer {
    private static Logger LOGGER = Logger.getLogger(Class.class.getName());

    public static void print2dArray(Double[][] distances) {
        //
        double[][] vector = new double[distances.length][distances[0].length];
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector[0].length; j++) {
                vector[i][j] = distances[i][j];
            }
        }
        print2dArray(vector);
    }

    public static void print2dArray(double[][] distances) {
        //
        StringBuilder builder = new StringBuilder();
        builder.append("distances:\n");
        // print inverse
        for (int i = 0; i < distances.length; i++) {
            builder.append("[");
            for (int j = 0; j < distances[i].length; j++) {
                builder.append(String.format("%6.3f", distances[i][j]));
                if (j < distances[i].length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]\n");
        }
        LOGGER.info(builder.toString());
    }

    public static void print2dArrayInt(Integer[][] distances) {
        //
        StringBuilder builder = new StringBuilder();
        builder.append("distances:\n");
        // print inverse
        for (int i = 0; i < distances.length; i++) {
            builder.append("[");
            for (int j = 0; j < distances[i].length; j++) {
                builder.append(String.format("%4d", distances[i][j]));
                if (j < distances[i].length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]\n");
        }
        LOGGER.info(builder.toString());
    }

    public static void print2dArrayInt(List<List<Integer>> distances) {
        Integer[][] vector = new Integer[distances.size()][distances.get(0).size()];
        for (int i = 0; i < distances.size(); i++) {
            List<Integer> row = distances.get(i);
            for (int j = 0; j < row.size(); j++) {
                vector[i][j] = row.get(j);
            }
        }
        print2dArrayInt(vector);
    }

    public static void print2dArray(List<List<Double>> distances) {
        Double[][] vector = new Double[distances.size()][distances.get(0).size()];
        for (int i = 0; i < distances.size(); i++) {
            List<Double> row = distances.get(i);
            for (int j = 0; j < row.size(); j++) {
                vector[i][j] = row.get(j);
            }
        }
        print2dArray(vector);
    }
}

package io.github.henryw.utils;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ResourceReader {

    public Set<String> getStopwords(String fileName) {

        Set<String> result = new HashSet<>();

        //Get file from resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            result.add(line);
        }
        scanner.close();

        return result;
    }
}

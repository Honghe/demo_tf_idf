package io.github.henryw;

import java.util.*;

public class CountVectorizer {
    private List<String> featureName = new ArrayList<String>();
    private List<List<Integer>> vectors = new ArrayList<List<Integer>>();

    private Map<String, Integer> countOccurences(List<String> list) {
        Map<String, Integer> count = new HashMap<String, Integer>();
        for (String t : list) {
            Integer i = count.get(t);
            if (i == null) {
                i = 0;
            }
            count.put(t, i + 1);
        }
        return count;
    }

    public List<List<Integer>> fit_transform(List<List<String>> list) {
        List<Map<String, Integer>> listCount = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < list.size(); i++) {
            listCount.add(countOccurences(list.get(i)));
        }
        Set<String> keysSet = new HashSet<String>();
        for (int i = 0; i < listCount.size(); i++) {
            keysSet.addAll(listCount.get(i).keySet());
        }
        List<String> keys = new ArrayList<String>(keysSet);
        Collections.sort(keys);
        this.featureName = keys;

        for (int i = 0; i < listCount.size(); i++) {
            List<Integer> vector = new ArrayList<Integer>();
            Map<String, Integer> map = listCount.get(i);
            for (int j = 0; j < keys.size(); j++) {
                String key = keys.get(j);
                if (map.containsKey(key)) {
                    vector.add(map.get(key));
                } else {
                    vector.add(0);
                }
            }
            vectors.add(vector);
        }
        return vectors;
    }

    public List<String> get_feature_names() {
        return this.featureName;
    }
}

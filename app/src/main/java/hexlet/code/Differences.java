package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differences {
    public static Map<String, Object> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> resultMap = new TreeMap<>();

        TreeSet<String> allKey = new TreeSet<>();
        allKey.addAll(map1.keySet());
        allKey.addAll(map2.keySet());

        for (String key : allKey) {

            if (!dataFileOne.containsKey(key)) {
                differ.put(key, new Item(newValue, ADDED));
            } else if (!dataFileTwo.containsKey(key)) {
                differ.put(key, new Item(oldValue, DELETED));
            } else if (Objects.equals(oldValue, newValue)) {
                differ.put(key, new Item(oldValue, newValue, UNCHANGED));
            } else {
                differ.put(key, new Item(oldValue, newValue, CHANGED));
            }
        }

        return resultMap;
    }
}

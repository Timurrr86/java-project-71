package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differences {
    public static List<Map<String, Object>> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> resultMap = new ArrayList<>();

        Set<String> allKey = new TreeSet<>();
        allKey.addAll(map1.keySet());
        allKey.addAll(map2.keySet());
        for (String key : allKey) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "removed");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("status", "added");
            } else if (!map1.get(key).equals(map2.get(key))) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("status", "changed");
            } else {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "unchanged");
            }
            resultMap.add(map);
        }
        return resultMap;
    }
}

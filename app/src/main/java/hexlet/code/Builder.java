package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.Map;
import java.util.LinkedHashMap;

public class Builder {
    public static List<Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        List<Map<String, Object>> result = new LinkedList<>();
        for (var key :keys) {
            //Object value1 = data1.get(key);
            //Object value2 = data2.get(key);
            Map<String, Object> data = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "deleted");
                data.put("key", key);
                data.put("newValue", value1);
                //result.add(node);
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "added");
                data.put("key", key);
                data.put("newValue", value2);
                //result.add(node);
            } else if (!Object.equals(value1, value2)) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "changed");
                data.put("key", key);
                data.put("oldValue", value1);
                data.put("newValue", value2);
                //result.add(node);
            } else if (Object.equals(value1, value2)) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "unchanged");
                data.put("key", key);
                data.put("newValue", value1);
                //result.add(node);
            } else {
                throw new RuntimeException("Unknown type: " + key);
            }
            result.add(map);
        }
        return result;
    }    
}

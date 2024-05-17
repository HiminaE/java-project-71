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
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            //Map<String, Object> data = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "deleted");
                node.put("key", key);
                node.put("newValue", data1.get(key));
                result.add(node);
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "added");
                node.put("key", key);
                node.put("newValue", data2.get(key));
                result.add(node);
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "changed");
                node.put("key", key);
                node.put("oldValue", data1.get(key));
                node.put("newValue", data2.get(key));
                result.add(node);
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "unchanged");
                node.put("key", key);
                node.put("newValue", data1.get(key));
                result.add(node);
            } else {
                throw new RuntimeException("Unknown type: " + key);
            }
        }
        return result;
    }
}

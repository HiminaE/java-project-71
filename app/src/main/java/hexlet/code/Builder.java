package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.Map;
import java.util.LinkedHashMap;

public class Builder {
    public static List<Map<String, Object>> build(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> keys = new TreeSet<>(file1.keySet());
        keys.addAll(file2.keySet());
        List<Map<String, Object>> result = new LinkedList<>();
        for (var key : keys) {
            Object value1 = file1.get(key) == null ? "null" : file1.get(key);
            Object value2 = file2.get(key) == null ? "null" : file2.get(key);
            if (Objects.equals(value1, value2)) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "unchanged");
                node.put("key", key);
                node.put("newValue", value1);
                result.add(node);
            } else if (file1.containsKey(key) && !file2.containsKey(key)) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "deleted");
                node.put("key", key);
                node.put("newValue", value1);
                result.add(node);
            } else if (file2.containsKey(key) && !file1.containsKey(key)) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "added");
                node.put("key", key);
                node.put("newValue", value2);
                result.add(node);
            } else {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "changed");
                node.put("key", key);
                node.put("oldValue", value1);
                node.put("newValue", value2);
                result.add(node);
            }
        }
        return result;
    }
}
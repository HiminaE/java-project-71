package hexlet.code;

import java.util.ArrayList;
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
        List<Map<String, Object>> result = new ArrayList<>();
        for (var key :keys) {
            //Object value1 = data1.get(key);
            //Object value2 = data2.get(key);
            Map<String, Object> data = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "deleted");
                data.put("key", key);
                data.put("newValue", data1.get(key));
                //result.add(node);
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "added");
                data.put("key", key);
                data.put("newValue", data2.get(key));
                //result.add(node);
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "changed");
                data.put("key", key);
                data.put("oldValue", data1.get(key));
                data.put("newValue", data2.get(key));
                //result.add(node);
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                //Map<String, Object> node = new LinkedHashMap<>();
                data.put("type", "unchanged");
                data.put("key", key);
                data.put("newValue", data1.get(key));
                //result.add(node);
            } else {
                throw new RuntimeException("Unknown type: " + key);
            }
            result.add(data);
        }
        return result;
    }
}

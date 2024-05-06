package hexlet.code.formaters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylishFormat(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (var d : diff) {
            if (d.get("type").equals("deleted")) {
                result.append("  - ").append(d.get("key")).append(": ").append(d.get("newValue")).append("\n");
            }
            if (d.get("type").equals("added")) {
                result.append("  + ").append(d.get("key")).append(": ").append(d.get("newValue")).append("\n");
            }
            if (d.get("type").equals("unchanged")) {
                result.append("    ").append(d.get("key")).append(": ").append(d.get("newValue")).append("\n");
            }
            if (d.get("type").equals("changed")) {
                result.append("  - ").append(d.get("key")).append(": ").append(d.get("oldValue")).append("\n");
                result.append("  + ").append(d.get("key")).append(": ").append(d.get("newValue")).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}

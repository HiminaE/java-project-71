package hexlet.code.formaters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String plainFormat(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (var d : diff) {
            if (d.get("type").equals("deleted")) {
                result.append("Property '").append(d.get("key")).append("' was removed").append("\n");
            } else if (d.get("type").equals("added")) {
                result.append("Property '").append(d.get("key")).append("' was added with value: ")
                        .append(convertedValue(d.get("newValue")))
                        .append("\n");
            } else if (d.get("type").equals("changed")) {
                result.append("Property '").append(d.get("key")).append("' was updated. From ")
                        .append(convertedValue(d.get("oldValue"))).append(" to ")
                        .append(convertedValue(d.get("newValue"))).append("\n");
            }
        }
        return result.toString().trim();
    }
    public static String convertedValue(Object value) {
        if (value.equals("null")) {
            return null;
        } else if (value instanceof Integer) {
            return value.toString();
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Boolean) {
            return value.toString();
        }
        return "[complex value]";
    }
}

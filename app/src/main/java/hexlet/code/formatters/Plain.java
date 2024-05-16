package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;

public class Plain {
    public static String plainFormat(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (var d : diff) {
            if (d.get("type").equals("deleted")) {
                result.append("Property '").append(d.get("key")).append("' was removed").append("\n");
            } else if (d.get("type").equals("added")) {
                result.append("Property '").append(d.get("key")).append("' was added with value: ")
                        .append(convertedValue(d.get("newValue"))) .append("\n");
            } else if (d.get("type").equals("changed")) {
                result.append("Property '").append(d.get("key")).append("' was updated. From ")
                        .append(convertedValue(d.get("oldValue"))).append(" to ")
                        .append(convertedValue(d.get("newValue"))).append("\n");
            } else if (d.get("type").equals("unchanged")) {
                result.append("");
            } else {
                throw new RuntimeException("Unknown status: " + d.get("type"));
            }
        }
        return result.toString().trim();
    }

    public static String convertedValue(Object value) {
        if (value instanceof Object[] || value instanceof Collections || value instanceof Map
                || value instanceof ArrayList<?>) {
            return "[complex value]";
        } else if (value == null) {
            return "null";
        } else if (value instanceof  String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}

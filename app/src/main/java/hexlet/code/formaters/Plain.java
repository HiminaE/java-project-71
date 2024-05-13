package hexlet.code.formaters;

import java.util.Collection;
import java.util.Map;

public class Plain {
    public static String plainFormat(Map<String, Object> diff) {
        String type = (String) diff.get("type");
        if (type == "deleted") {
            return "Property '" + diff.get("key") + "' was removed" + "\n";
        } else if (type == "added") {
            return "Property '" + diff.get("key") + "' was added with value: " + convertedValue(diff.get("newValue")) + "\n";
        } else if (type == "changed") {
            return "Property '" + diff.get("key") + "' was updated. From " + convertedValue(diff.get("oldValue"))
                    + " to " + convertedValue(diff.get("newValue")) + "\n";
        } else if (type == "unchanged") {
            return "";
        } else {
            throw new RuntimeException("Unknown type");
        }
    }
    public static String convertedValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof Collection<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}

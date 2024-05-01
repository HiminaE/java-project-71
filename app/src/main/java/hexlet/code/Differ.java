package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> file1 = getData(filepath1);
        Map<String, Object> file2 = getData(filepath2);
        List<Map<String, Object>> diff = build(file1, file2);
        return chooseFormat(diff, format);
    };
    public static String jsonFormat(List<Map<String, Object>> diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }
    public static Map<String, Object> getData(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String fileContent = Files.readString(path);
        String format = getFileFormat(filePath);
        Map<String, Object> parsedData = new LinkedHashMap<>();
        Map<String, Object> parsedMap = parser(fileContent, format);

        for (Map.Entry<String, Object> entry : parsedMap.entrySet()) {
            parsedData.put(entry.getKey(), entry.getValue());
        }
        return parsedData;
    }
    public static String getFileFormat(String path) {
        return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
    }
    public static Map<String, Object> parser(String fileContent, String format) throws IOException {
        Map<String, Object> result = new HashMap<>();
        if (format.equals("json")) {
            result = parsingJson(fileContent);
        } else if (format.equals("yml") || format.equals("yaml")) {
            result = parsingYml(fileContent);
        } else {
            throw new RuntimeException("Неверный формат: " + format);
        }
        return result;
    }
    public static Map<String, Object> parsingYml(String fileContent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
    }
    public static Map<String, Object> parsingJson(String fileContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<String, Object> linkedMap = mapper.readValue(fileContent,
                new TypeReference<LinkedHashMap<String, Object>>() { });

        return new LinkedHashMap<>(linkedMap);
    }
    public static List<Map<String, Object>> build(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> keys = new TreeSet<>(file1.keySet());
        keys.addAll(file2.keySet());
        List<Map<String, Object>> result = new LinkedList<>();
        for (var key :keys) {
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
    public static String chooseFormat(List<Map<String, Object>> diff, String format) throws JsonProcessingException {
        String result = switch (format) {
            case "stylish" -> stylishFormat(diff);
            case "plain" -> plainFormat(diff);
            case "json" -> jsonFormat(diff);
            default -> throw new RuntimeException("Неверный формат: " + format);
        };
        return result;
    }
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
    public static String plainFormat(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (var d : diff) {
            if (d.get("type").equals("deleted")) {
                result.append("Property '").append(d.get("key")).append("' was removed").append("\n");
            }
            if (d.get("type").equals("added")) {
                result.append("Property '").append(d.get("key")).append("' was added with value: ")
                        .append(convertedValue(d.get("newValue"))) .append("\n");
            }
            if (d.get("type").equals("changed")) {
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


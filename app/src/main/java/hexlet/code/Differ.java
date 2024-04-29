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


public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
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
        } else {
            throw new RuntimeException("Неверный формат: " + format);
        }
        return result;
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
        String result = "";
        if (format.equals("json") || format.equals("stylish")) {
            result = jsonFormat(diff);
        }  else {
            throw new RuntimeException("Неверный формат: " + format);
        }
        return result;
    }
}
package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
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
}

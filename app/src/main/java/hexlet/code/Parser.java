package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsingYml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, Map.class);
    }

    public static Map<String, Object> parsingJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static Map<String, Object> parser(String content, String format) throws Exception {
        switch (format) {
            case "yml":
            case "yaml":
                return parsingYml(content);
            case "json":
                return parsingJson(content);
            default:
                throw new Exception("Unknown format: '" + format + "'");
        }
    }
}

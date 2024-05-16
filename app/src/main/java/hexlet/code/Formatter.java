package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(List<Map<String, Object>> diff, String format) throws JsonProcessingException {
        String result = switch (format) {
            case "stylish" -> Stylish.stylishFormat(diff);
            case "plain" -> Plain.plainFormat(diff);
            case "json" -> Json.jsonFormat(diff);
            default -> throw new RuntimeException("Неверный формат: " + format);
        };
        return result;
    }
}

package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formaters.Json;
import hexlet.code.formaters.Plain;
import hexlet.code.formaters.Stylish;

import java.util.List;
import java.util.Map;

import static hexlet.code.formaters.Json.jsonFormat;
import static hexlet.code.formaters.Plain.plainFormat;
import static hexlet.code.formaters.Stylish.stylishFormat;

public class Formater {
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

package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;

public class Differ {
    public static String generate(String path1, String path2, String format) throws IOException {
        List<Map<String, Object>> differResult = new ArrayList<>();
        Path realPath1 = Paths.get(path1).toAbsolutePath().normalize();
        Path realPath2 = Paths.get(path2).toAbsolutePath().normalize();
        Map<String, Object> fileData1 = Parser.getParser(realPath1.toString());
        Map<String, Object> fileData2 = Parser.getParser(realPath2.toString());
        Set<String> allKeys = new TreeSet<>(fileData1.keySet());
        allKeys.addAll(fileData2.keySet());
        for (var key : allKeys) {
            if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                differResult.add(Map.of("Key", key, "status", "removed",
                        "old_value", fileData1.get(key)));
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                differResult.add(Map.of("Key", key, "status", "added",
                        "old_value", fileData2.get(key)));
            } else if (fileData1.containsKey(key) && fileData2.containsKey(key)
                    && Objects.equals(fileData1.get(key), fileData2.get(key))) {
                differResult.add(Map.of("Key", key, "status", "no_changes",
                        "old_value", fileData2.get(key)));
            } else {
                differResult.add(Map.of("Key", key, "status", "edited",
                        "old_value", fileData1.get(key), "new_value", fileData2.get(key)));
            }
        }

    }
}

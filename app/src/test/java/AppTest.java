import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class DiffTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    public static String fileToString(String fileName) throws Exception {
        Path path = filePath(fileName);
        return Files.readString(path);
    }
    public static Path filePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
    }
    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = fileToString("outputJson.json");
        resultStylish = fileToString("outputStylish.txt");
        resultPlain = fileToString("outputPlain.txt");
    }
    @Test
    public void testYml() throws Exception {
        String result = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }
    @Test
    public void testJson() throws Exception {
        String result = Differ.generate("src/test/resources/fileForest1.json", "src/test/resources/fileForTest2.json");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }
    @Test
    public void testYmlStylish() throws Exception {
        String result = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml",
                "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }
    @Test
    public void testJsonStylish() throws Exception {
        String result = Differ.generate("src/test/resources/fileForTest1.json",
                "src/test/resources/fileForTest2.json", "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(resultStylish);
    }
    @Test
    public void testYmlPlain() throws Exception {
        String result = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml",
                "plain");
        assertThat(result).isEqualToIgnoringWhitespace(resultPlain);
    }
    @Test
    public void testJsonPlain() throws Exception {
        String result = Differ.generate("src/test/resources/fileForTest1.json",
                "src/test/resources/fileForTest2.json", "plain");
        assertThat(result).isEqualToIgnoringWhitespace(resultPlain);
    }
}

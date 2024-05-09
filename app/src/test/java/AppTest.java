import hexlet.code.Differ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class AppTest {
    private final String filePath1 = "src/test/resources/test3.json";
    private final String filePath2 = "src/test/resources/test4.json";
    private final String filePath3 = "src/test/resources/test1.yml";
    private final String filePath4 = "src/test/resources/test2.yml";

    private final Path pathStylish = Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize();
    private final Path pathPlain = Paths.get("src/test/resources/outputPlain.txt").toAbsolutePath().normalize();
    private final Path pathJson = Paths.get("src/test/resources/outputJson.json").toAbsolutePath().normalize();
    @Test
    public void testJson() throws Exception {
        var actual = Differ.generate(filePath1, filePath2);
        String expected = Files.readString(pathStylish);
        assertEquals(expected, actual);
    }
    @Test
    public void testYml() throws Exception {
        var actual = Differ.generate(filePath3, filePath4);
        String expected = Files.readString(pathStylish);
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonStylish() throws Exception {
        var actual = Differ.generate(filePath1, filePath2, "stylish");
        String expected = Files.readString(pathStylish);
        assertEquals(expected, actual);
    }
    @Test
    public void testYmlStylish() throws Exception {
        var actual = Differ.generate(filePath3, filePath4, "stylish");
        String expected = Files.readString(pathStylish);
        assertEquals(expected, actual);
    }
    @Test
    public void testYmlPlain() throws Exception {
        var actual = Differ.generate(filePath3, filePath4, "plain");
        String expected = Files.readString(pathPlain);
        assertEquals(expected, actual);
    }
    @Test
    public void testJsonPlain() throws Exception {
        var actual = Differ.generate(filePath1, filePath2, "plain");
        String expected = Files.readString(pathPlain);
        assertEquals(expected, actual);
    }
    @Test
    public void testYmlJson() throws Exception {
        var actual = Differ.generate(filePath3, filePath4, "json");
        String expected = Files.readString(pathJson);
        assertEquals(expected, actual);
    }
    @Test
    public void testJsonJson() throws Exception {
        var actual = Differ.generate(filePath1, filePath2, "json");
        String expected = Files.readString(pathJson);
        assertEquals(expected, actual);
    }
    
}

import hexlet.code.Differ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AppTest {
    @Test
    public void testJson() throws Exception {
        var actual = Differ.generate("src/test/resources/test3.json", "src/test/resources/test4.json");
        String expected = Files.readString(Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
    @Test
    public void testYml() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml");
        String expected = Files.readString(Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonStylish() throws Exception {
        var actual = Differ.generate("src/test/resources/test3.json", "src/test/resources/test4.json", "stylish");
        String expected = Files.readString(Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
    @Test
    public void testYmlStylish() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml", "stylish");
        String expected = Files.readString(Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
    @Test
    public void testYmlPlain() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml", "plain");
        String expected = Files.readString(Paths.get("src/test/resources/outputPlain.txt").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
    @Test
    public void testJsonPlain() throws Exception {
        var actual = Differ.generate("src/test/resources/test3.json", "src/test/resources/test4.json", "plain");
        String expected = Files.readString(Paths.get("src/test/resources/outputPlain.txt").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
    @Test
    public void testYmlJson() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.yml", "src/test/resources/test2.yml", "json");
        String expected = Files.readString(Paths.get("src/test/resources/outputJson.json").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
    @Test
    public void testJsonJson() throws Exception {
        var actual = Differ.generate("src/test/resources/test3.json", "src/test/resources/test4.json", "json");
        String expected = Files.readString(Paths.get("src/test/resources/outputJson.json").toAbsolutePath().normalize());
        assertEquals(expected, actual);
    }
}


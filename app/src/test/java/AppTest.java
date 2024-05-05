package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AppTest {
    @Test
    public void test1() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.json", "test2.json");
        String expected = Files.readString(Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize();
        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.json", "test2.json", "stylish");
        String expected = Files.readString(Paths.get("src/test/resources/outputStylish.txt").toAbsolutePath().normalize();
        assertEquals(expected, actual);
    }
    @Test
    public void test3() throws Exception {
        var actual = Differ.generate("src/test/resources/test1.yml", "test2.yml", "plain");
        String expected = Files.readString(Paths.get("src/test/resources/outputYml.txt").toAbsolutePath().normalize();
        assertEquals(expected, actual);
    }
    @Test
    public void test4() throws Exception {
        var actual = Differ.generate("src/test/resources/test3.json", "test4.json", "plain");
        String expected = Files.readString(Paths.get("src/test/resources/outputYml.txt").toAbsolutePath().normalize();
        assertEquals(expected, actual);
    }

}

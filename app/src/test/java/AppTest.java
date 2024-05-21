import hexlet.code.Differ;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class AppTest {
    private final String testDirectory = Paths.get("src", "test", "resources").toString();
    private String stylishResult;
    private String plainResult;
    private String jsonResult;
    private String defaultResult;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }
    @BeforeAll
    public static void beforeAll() throws Exception {
        stylishResult = readFixture("result_json.json");
        plainResult = readFixture("result_plain.json");
        jsonResult = readFixture("result_stylish.json");;
        defaultResult = stylishResult;
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1" + format);
        String filePath2 = getFixturePath("file2" + format);
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(plainResult);
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(jsonResult);
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(defaultResult);
    }
}

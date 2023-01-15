package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static String resultBlank;

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
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
        resultBlank = readFixture("result_blank.txt");
    }

    @Test
    public void testEmptyFile() throws Exception {
        String expected = generate("blankFile.json", "blankFile.json");

        assertThat(expected).isEqualTo(resultBlank);
    }

    @Test
    public void testUnknownFormat() {
        Throwable thrown = assertThrows(Exception.class, () -> generate("file.abc", "file.abc"));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testStylishJson() throws Exception {
        String expected = generate("file1.json", "file2.json");

        assertThat(expected).isEqualTo(resultStylish);
    }

    @Test
    public void testStylishYml() throws Exception {
        String expected = generate("file1.yml", "file2.yml");

        assertThat(expected).isEqualTo(resultStylish);
    }

    @Test
    public void testPlainJson() throws Exception {
        String expected = generate("file1.json", "file2.json", "plain");

        assertThat(expected).isEqualTo(resultPlain);
    }

    @Test
    public void testPlainYml() throws Exception {
        String expected = generate("file1.yml", "file2.yml", "plain");

        assertThat(expected).isEqualTo(resultPlain);
    }

    @Test
    public void testJsonFromJson() throws Exception {
        String expected = generate("file1.json", "file2.json", "json");

        assertEquals(expected, resultJson);
    }

    @Test
    public void testJsonFromYml() throws Exception {
        String expected = generate("file1.yml", "file2.yml", "json");

        assertThat(expected).isEqualTo(resultJson);
    }
}

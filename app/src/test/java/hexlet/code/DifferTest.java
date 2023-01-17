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
    private static final String FILE1_JSON = "src/test/resources/file1.json";
    private static final String FILE2_JSON = "src/test/resources/file2.json";
    private static final String FILE1_YML = "src/test/resources/file1.yml";
    private static final String FILE2_YML = "src/test/resources/file2.yml";
    private static final String BLANK_FILE = "src/test/resources/blankFile.json";
    private static final String INCORRECT_FILE_FORMAT = "src/test/resources/file.abc";

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
        String expected = generate(BLANK_FILE, BLANK_FILE);

        assertThat(expected).isEqualTo(resultBlank);
    }

    @Test
    public void testUnknownFormat() {
        Throwable thrown = assertThrows(Exception.class, () -> generate(INCORRECT_FILE_FORMAT, INCORRECT_FILE_FORMAT));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testStylishJson() throws Exception {
        String expected = generate(FILE1_JSON, FILE2_JSON, "stylish");
        assertThat(expected).isEqualTo(resultStylish);
    }

    @Test
    public void testStylishYml() throws Exception {
        String expected = generate(FILE1_YML, FILE2_YML, "stylish");
        assertThat(expected).isEqualTo(resultStylish);
    }

    @Test
    public void testPlainJson() throws Exception {
        String expected = generate(FILE1_JSON, FILE2_JSON, "plain");
        assertThat(expected).isEqualTo(resultPlain);
    }

    @Test
    public void testPlainYml() throws Exception {
        String expected = generate(FILE1_YML, FILE2_YML, "plain");
        assertThat(expected).isEqualTo(resultPlain);
    }

    @Test
    public void testJsonFromJson() throws Exception {
        String expected = generate(FILE1_JSON, FILE2_JSON, "json");
        assertEquals(expected, resultJson);
    }

    @Test
    public void testJsonFromYml() throws Exception {
        String expected = generate(FILE1_YML, FILE2_YML, "json");
        assertThat(expected).isEqualTo(resultJson);
    }
}

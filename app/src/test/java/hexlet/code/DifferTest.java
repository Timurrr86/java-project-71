package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    @Test
    public void testJson() throws Exception {
        String expected = generate("src/test/resources/file1.json", "src/test/resources/file2.json");

        Path path = Paths.get("src/test/resources/testFile.json").toAbsolutePath().normalize();
        String content = Files.readString(path);

        assertThat(expected).isEqualTo(content);
    }

    @Test
    public void testYml() throws Exception {
        String expected = generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");

        Path path = Paths.get("src/test/resources/testFile.yml").toAbsolutePath().normalize();
        String content = Files.readString(path);

        assertThat(expected).isEqualTo(content);
    }

    @Test
    public void testEmptyFile() throws Exception {
        String expected = generate("src/test/resources/blankFile.json", "src/test/resources/blankFile.json");

        Path path = Paths.get("src/test/resources/testBlank.json").toAbsolutePath().normalize();
        String content = Files.readString(path);

        assertThat(expected).isEqualTo(content);
    }

    @Test
    public void testUnknownFormat() throws Exception {
        Throwable thrown = assertThrows(Exception.class, () -> {
            generate("src/test/resources/file.abc", "src/test/resources/file.abc");
        });
        assertNotNull(thrown.getMessage());
    }
}

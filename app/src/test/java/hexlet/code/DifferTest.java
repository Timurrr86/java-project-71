package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @Test
    public void test1() throws Exception {
        var expected = generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        Path path = Paths.get("src/test/resources/testFile.json").toAbsolutePath().normalize();
        String content = Files.readString(path);

        assertThat(expected).isEqualTo(content);
    }


}

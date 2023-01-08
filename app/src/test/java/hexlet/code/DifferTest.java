package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.Differ.generate;
import static hexlet.code.Differ.getContent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    @Test
    public void testEmptyFile() throws Exception {
        String expected = generate("src/test/resources/blankFile.json", "src/test/resources/blankFile.json");
        String content = getContent("src/test/resources/testBlank.json");

        assertThat(expected).isEqualTo(content);
    }

    @Test
    public void testUnknownFormat() {
        Throwable thrown = assertThrows(Exception.class, () -> {
            generate("src/test/resources/file.abc", "src/test/resources/file.abc");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testNestedJson() throws Exception {
        String expected = generate("src/test/resources/step8_1.json", "src/test/resources/step8_2.json");
        String content = getContent("src/test/resources/step8_result.json");

        assertThat(expected).isEqualTo(content);
    }

    @Test
    public void testNestedYml() throws Exception {
        String expected = generate("src/test/resources/step8_1.yml", "src/test/resources/step8_2.yml");
        String content = getContent("src/test/resources/step8_result.yml");

        assertThat(expected).isEqualTo(content);
    }

    @Test
    public void testPlain() throws Exception {
        String expected = generate("src/test/resources/step8_1.json", "src/test/resources/step8_2.json");
        String content = getContent("src/test/resources/step9_plain.json");

        assertThat(expected).isEqualTo(content);
    }
}

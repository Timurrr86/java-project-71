package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Differences.getDifferences;
import static hexlet.code.formatters.Formatter.makeFormat;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        String content1 = getContent(filePath1);
        String content2 = getContent(filePath2);

        String extension1 = filePath1.substring(filePath1.indexOf(".") + 1);
        String extension2 = filePath2.substring(filePath2.indexOf(".") + 1);

        Map<String, Object> map1 = Parser.parser(content1, extension1);
        Map<String, Object> map2 = Parser.parser(content2, extension2);

        return makeFormat(getDifferences(map1, map2), format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String getContent(String filePath) throws Exception {
        Path path = Paths.get(filePath.substring(filePath.indexOf("src"))).toAbsolutePath().normalize();
        return Files.readString(path);
    }

}

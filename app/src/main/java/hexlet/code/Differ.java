package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Differences.getDifferences;


public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        ObjectMapper objectMapper1 = new ObjectMapper();
        ObjectMapper objectMapper2 = new ObjectMapper();
        Map<String, Object> map1 = objectMapper1.readValue(content1, new TypeReference<>() {});
        Map<String, Object> map2 = objectMapper2.readValue(content2, new TypeReference<>() {});

//        System.out.println(output(getDifferences(map1, map2)));

//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(output(getDifferences(map1, map2)));
        return output(getDifferences(map1, map2));

    }

    public static String output(List<Map<String, Object>> resultMap) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> records : resultMap) {
            switch (records.get("status").toString()) {
                case "removed":
                    result.append(" - ").append(records.get("key")).append(": ")
                            .append(records.get("oldValue")).append("\n");
                    break;
                case "added":
                    result.append(" + ").append(records.get("key")).append(": ")
                            .append(records.get("newValue")).append("\n");
                    break;
                case "unchanged":
                    result.append("   ").append(records.get("key")).append(": ")
                            .append(records.get("oldValue")).append("\n");
                    break;
                default:
                    result.append(" - ").append(records.get("key")).append(": ")
                            .append(records.get("oldValue")).append("\n");
                    result.append(" + ").append(records.get("key")).append(": ")
                            .append(records.get("newValue")).append("\n");
                    break;
            }
        }
        result.append("}");
        return result.toString();
    }
}

package hexlet.code;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Differences.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        
        System.out.println(path1);
        System.out.println(path2);
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        System.out.println(content1);
        System.out.println(content2);

        ObjectMapper objectMapper1 = new ObjectMapper();
        ObjectMapper objectMapper2 = new ObjectMapper();
        Map<String, Object> map1 = objectMapper1.readValue(content1, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> map2 = objectMapper2.readValue(content2, new TypeReference<Map<String,Object>>(){});

        System.out.println(map1);
        System.out.println(map2);

        System.out.println(getDifferences(map1, map2));

        return "";
    }
}

package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getContent(String file, String extension) throws Exception {
        if (file.isBlank() || file.isEmpty()) {
            return new HashMap<>();
        }
        if (extension.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } else if (extension.equals("yml") || extension.equals("yaml")) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } else {
            throw new Exception("Unknown format");
        }

    }
}

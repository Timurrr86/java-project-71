package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String file, String data) throws Exception {
        if (file.isBlank() || file.isEmpty()) {
            return new HashMap<>();
        }
        switch (data) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(file, new TypeReference<>() {
                });
            }
            case "yml", "yaml" -> {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                return objectMapper.readValue(file, new TypeReference<>() {
                });
            }
            default -> throw new Exception("Unknown format" + data);
        }
    }
}

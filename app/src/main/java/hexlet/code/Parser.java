package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String data, String type) throws Exception {
        if (data.isBlank() || data.isEmpty()) {
            return new HashMap<>();
        }
        switch (type) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(data, new TypeReference<>() {
                });
            }
            case "yml", "yaml" -> {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                return objectMapper.readValue(data, new TypeReference<>() {
                });
            }
            default -> throw new Exception("Unknown format" + type);
        }
    }
}

package hexlet.code.formatters;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String makePlain(List<Map<String, Object>> resultMap) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> records : resultMap) {
            String oldValue = checkValue(records.get("oldValue"));
            String newValue = checkValue(records.get("newValue"));
            switch (records.get("status").toString()) {
                case "removed":
                    result.append("Property '").append(records.get("key")).append("' was removed\n");
                    break;
                case "added":
                    result.append("Property '").append(records.get("key")).append("' was added with value: ")
                            .append(newValue).append("\n");
                    break;
                case "unchanged":
                    break;
                default:
                    result.append("Property '").append(records.get("key")).append("' was updated. From ")
                            .append(oldValue).append(" to ")
                            .append(newValue).append("\n");
                    break;
            }
        }
        return result.toString();
    }
    public static String checkValue(Object value) {
        if (value instanceof Map || value instanceof List<?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return null;
        }
        return value.toString();
    }
}

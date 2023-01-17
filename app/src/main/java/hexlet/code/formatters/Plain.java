package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String makePlain(List<Map<String, Object>> resultMap) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> records : resultMap) {
            String oldValue = checkValue(records.get("oldValue"));
            String newValue = checkValue(records.get("newValue"));
            switch (records.get("status") != null ? records.get("status").toString() : "") {
                case "removed":
                    result.append("Property '").append(records.get("key")).append("' was removed\n");
                    break;
                case "added":
                    result.append("Property '").append(records.get("key")).append("' was added with value: ")
                            .append(newValue).append("\n");
                    break;
                case "unchanged":
                    break;
                case "changed":
                    result.append("Property '").append(records.get("key")).append("' was updated. From ")
                            .append(oldValue).append(" to ")
                            .append(newValue).append("\n");
                    break;
                default:
                    throw new Error("Unknown status!" + records.get("status"));
            }
        }
        return result.toString().trim();
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

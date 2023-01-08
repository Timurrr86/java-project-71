package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String makeStylish(List<Map<String, Object>> resultMap) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> records : resultMap) {
            switch (records.get("status").toString()) {
                case "removed":
                    result.append("  - ").append(records.get("key")).append(": ")
                            .append(records.get("oldValue")).append("\n");
                    break;
                case "added":
                    result.append("  + ").append(records.get("key")).append(": ")
                            .append(records.get("newValue")).append("\n");
                    break;
                case "unchanged":
                    result.append(" ".repeat(4)).append(records.get("key")).append(": ")
                            .append(records.get("oldValue")).append("\n");
                    break;
                default:
                    result.append("  - ").append(records.get("key")).append(": ")
                            .append(records.get("oldValue")).append("\n");
                    result.append("  + ").append(records.get("key")).append(": ")
                            .append(records.get("newValue")).append("\n");
                    break;
            }
        }
        result.append("}\n");
        return result.toString();
    }
}

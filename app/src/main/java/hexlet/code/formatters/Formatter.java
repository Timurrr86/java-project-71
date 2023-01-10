package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String makeFormat(List<Map<String, Object>> resultMap, String format) throws Exception {
        switch (format) {
            case "json":
                return Json.makeJson(resultMap);
            case "stylish":
                return Stylish.makeStylish(resultMap);
            case "plain":
                return Plain.makePlain(resultMap);
            default:
                throw new Exception("Unknown format: " + format);
        }
    }
}

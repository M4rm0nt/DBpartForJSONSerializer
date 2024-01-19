package serializers;

import utils.Util;

import java.util.Set;

public class StringSerializer extends DataSerializer {
    @Override
    public String serialize(Object object, Set<Object> visitedObjects, int indentLevel) {
        String str = (String) object;
        return String.format(Util.QUOTE_FORMAT, escapeString(str));
    }

    public static String escapeString(String string) {
        StringBuilder escaped = new StringBuilder();
        for (char c : string.toCharArray()) {
            switch (c) {
                case '\"': escaped.append("\\\""); break;
                case '\\': escaped.append("\\\\"); break;
                case '\b': escaped.append("\\b"); break;
                case '\f': escaped.append("\\f"); break;
                case '\n': escaped.append("\\n"); break;
                case '\r': escaped.append("\\r"); break;
                case '\t': escaped.append("\\t"); break;
                default: escaped.append(c);
            }
        }
        return escaped.toString();
    }
}
package serializers;

import factorys.JsonSerializerFactory;
import serializers.DataSerializer;

import java.lang.reflect.Field;
import java.util.*;

public class JsonSerializer {

    static JsonSerializerFactory factory = new JsonSerializerFactory();

    public static final Map<Class<?>, Field[]> fieldsCache = new HashMap<>();

    public String serialize(Object object) {
        return serializeInternal(object, new HashSet<>(), 0);
    }

    public String serializeList(List<?> objects) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < objects.size(); i++) {
            sb.append(serialize(objects.get(i)));
            if (i < objects.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static String serializeInternal(Object object, Set<Object> visitedObjects, int indentLevel) {
        if (object == null) {
            return "null";
        }
        if (visitedObjects.contains(object)) {
            return "\"[Circular Reference]\"";
        }
        visitedObjects.add(object);

        DataSerializer serializer = factory.getSerializer(object);
        return serializer.serialize(object, visitedObjects, indentLevel);
    }

}

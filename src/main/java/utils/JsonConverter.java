package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.Util.*;

public class JsonConverter {

    public static Map<String, List<String>> convertJsonToMap(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return Collections.emptyMap();
        }

        JSONArray jsonArray = new JSONArray(jsonString);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getJSONObject)
                .filter(jsonObject -> jsonObject.has(kategorie) && jsonObject.has(namen))
                .collect(Collectors.toMap(
                        jsonObject -> jsonObject.optString(kategorie, devaultVale),
                        jsonObject -> toList(jsonObject.optJSONArray(namen))
                ));
    }

    private static List<String> toList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return Collections.emptyList();
        }
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> jsonArray.optString(index, null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
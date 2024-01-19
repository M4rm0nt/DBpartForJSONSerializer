package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class JsonConverter {

    public static Map<String, List<String>> convertJsonToMap(String jsonString) {
        Map<String, List<String>> haustiereMap = new HashMap<>();

        if (jsonString != null && !jsonString.isEmpty()) {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String kategorie = jsonObject.getString("kategorie");
                JSONArray namenArray = jsonObject.getJSONArray("namen");

                List<String> namenListe = new ArrayList<>();
                for (int j = 0; j < namenArray.length(); j++) {
                    namenListe.add(namenArray.getString(j));
                }

                haustiereMap.put(kategorie, namenListe);
            }
        }

        return haustiereMap;
    }

}

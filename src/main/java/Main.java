import models.Mensch;
import serializers.JsonSerializer;
import services.MenschService;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MenschService service = new MenschService();
        JsonSerializer serializer = new JsonSerializer();

        List<Mensch> menschen = new ArrayList<>();
        menschen.add(service.getMenschByName("Alice"));
        menschen.add(service.getMenschByName("Bob"));
        menschen.add(service.getMenschByName("Cedrik"));

        for (Mensch mensch : menschen) {
            String json = serializer.serialize(mensch);
            System.out.println(json);
        }
    }
}

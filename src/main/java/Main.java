import models.Mensch;
import repositorys.DatabaseConnection;
import repositorys.MenschRepository;
import serializers.JsonSerializer;
import services.MenschService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        MenschRepository menschRepository = new MenschRepository(databaseConnection);
        MenschService service = new MenschService(menschRepository);
        JsonSerializer serializer = new JsonSerializer();

        /*
        Mensch mensch = service.getMenschByName("Alice");
        String json = serializer.serialize(mensch);

        System.out.println(json);
        */

        List<Mensch> menschen = new ArrayList<>();
        menschen.add(service.getMenschByName("Alice"));
        menschen.add(service.getMenschByName("Bob"));
        menschen.add(service.getMenschByName("Cedrik"));

        System.out.println(serializer.serializeList(menschen));
    }
}

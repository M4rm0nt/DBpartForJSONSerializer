import models.Mensch;
import repositorys.DatabaseConnection;
import repositorys.MenschRepository;
import serializers.JsonSerializer;
import services.MenschService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection databaseConnection = DatabaseConnection.getConnection();
        MenschRepository menschRepository = new MenschRepository(databaseConnection);
        MenschService service = new MenschService(menschRepository);
        JsonSerializer serializer = new JsonSerializer();

        List<Mensch> menschen = new ArrayList<>();
        menschen.add(service.getMenschByName("Alice"));
        menschen.add(service.getMenschByName("Bob"));
        menschen.add(service.getMenschByName("Cedrik"));

        System.out.println(serializer.serializeList(menschen));
    }
}
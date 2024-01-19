import service.MenschService;
import models.Mensch;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MenschService service = new MenschService();

        List<Mensch> menschen = new ArrayList<>();
        menschen.add(service.getMenschByName("Alice"));
        menschen.add(service.getMenschByName("Bob"));
        menschen.add(service.getMenschByName("Cedrik"));
        menschen.forEach(System.out::println);
    }
}

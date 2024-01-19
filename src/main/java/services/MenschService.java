package services;

import models.Mensch;
import repositorys.MenschRepository;

public class MenschService {
    private final MenschRepository repository = new MenschRepository();

    public Mensch getMenschByName(String name) {
        return repository.getMenschByName(name);
    }
}

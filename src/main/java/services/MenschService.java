package services;

import models.Mensch;
import repositorys.MenschRepository;
import exceptions.ServiceException;

public class MenschService {
    private final MenschRepository repository;

    public MenschService(MenschRepository repository) {
        this.repository = repository;
    }

    public Mensch getMenschByName(String name) {
        Mensch mensch = repository.getMenschByName(name);
        if (mensch == null) {
            throw new ServiceException("Fehler beim Abrufen des Menschen mit dem Namen " + name);
        }
        return mensch;
    }
}

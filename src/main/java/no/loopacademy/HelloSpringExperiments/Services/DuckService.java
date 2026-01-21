package no.loopacademy.HelloSpringExperiments.Services;

import no.loopacademy.HelloSpringExperiments.CustomExceptions.DuckNotFoundException;
import no.loopacademy.HelloSpringExperiments.CustomExceptions.InvalidDuckException;
import no.loopacademy.HelloSpringExperiments.DataAccess.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Models.Duck;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DuckService {

    private final DuckRepository repository;

    public DuckService(DuckRepository repository) {
        this.repository = repository;
    }

    public List<Duck> listDucks() {
        return repository.getAll();
    }

    public Duck getDuck(int id) {
        return repository.getById(id)
                .orElseThrow(() -> new DuckNotFoundException(id));
    }

    public Duck registerDuck(Duck duck) {
        // business rules belong here
        if (duck.getAge() < 0) {
            throw new InvalidDuckException("Age must be >= 0");
        }
        if (duck.getWeight() <= 0) {
            throw new InvalidDuckException("Weight must be > 0");
        }

        return repository.register(duck);
    }

    public void removeDuck(int id) {
        boolean removed = repository.unregisterById(id);
        if (!removed) {
            throw new IllegalArgumentException("Duck not found: " + id);
        }
    }

    public Duck updateDuck(int id, Duck duck) {
        // enforce path id as source of truth
        duck.setId(id);

        if (duck.getAge() < 0) {
            throw new IllegalArgumentException("Age must be >= 0");
        }
        if (duck.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be > 0");
        }

        boolean updated = repository.update(duck);
        if (!updated) {
            throw new DuckNotFoundException(id);
        }

        // either return the updated duck, or re-fetch (re-fetch is safer if DB mutates fields)
        return getDuck(id);
    }














}

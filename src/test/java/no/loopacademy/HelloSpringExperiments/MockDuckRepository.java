package no.loopacademy.HelloSpringExperiments;

import no.loopacademy.HelloSpringExperiments.Repositories.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Models.Duck;

import java.util.List;
import java.util.Optional;

public class MockDuckRepository implements DuckRepository {
    @Override
    public List<Duck> getAll() {
        return List.of();
    }

    @Override
    public Optional<Duck> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Duck register(Duck duck) {
        return null;
    }

    @Override
    public boolean update(Duck duck) {
        return false;
    }

    @Override
    public boolean unregisterById(int id) {
        return false;
    }
}

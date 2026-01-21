package no.loopacademy.HelloSpringExperiments.Adapter;

import no.loopacademy.HelloSpringExperiments.DataAccess.DuckCustomDataAccess;
import no.loopacademy.HelloSpringExperiments.DataAccess.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Models.Duck;

import java.util.List;
import java.util.Optional;

public class DuckRepoToDAOAdapter implements DuckRepository {

    DuckCustomDataAccess oldInterface;
    public DuckRepoToDAOAdapter(DuckCustomDataAccess oldInterface){
        this.oldInterface = oldInterface;
    }
public

    @Override
    public List<Duck> getAll() {

        oldInterface.findDucks();


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

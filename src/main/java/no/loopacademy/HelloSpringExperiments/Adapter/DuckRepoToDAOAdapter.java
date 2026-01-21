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

    // this class adapts the interface methods to the implementation of another interface - that's it
    @Override
    public List<Duck> getAll() {

        oldInterface.findDucks();

        return List.of();
    }

    @Override
    public Optional<Duck> getById(int id) {

        oldInterface.goFindARandomDuck();
        return Optional.empty();
    }

    @Override
    public Duck register(Duck duck) {
        oldInterface.hatchADuck(duck);

        return null;
    }

    @Override
    public boolean update(Duck duck) {

        oldInterface.ChangeADuck();

        return false;
    }

    @Override
    public boolean unregisterById(int id) {
        return false;
    }
}

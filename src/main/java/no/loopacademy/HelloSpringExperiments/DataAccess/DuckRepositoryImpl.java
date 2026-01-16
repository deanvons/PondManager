package no.loopacademy.HelloSpringExperiments.DataAccess;

import no.loopacademy.HelloSpringExperiments.Entities.Duck;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DuckRepositoryImpl implements  DuckRepository{

    private final DuckDataAccessOption dao;

    public DuckRepositoryImpl(DuckDataAccessOption dao) {
        this.dao = dao;
    }

    @Override
    public List<Duck> getAll() {
        return dao.selectAll();
    }

    @Override
    public Optional<Duck> getById(int id) {
        return dao.selectById(id);
    }

    @Override
    public Duck register(Duck duck) {
        return dao.insert(duck);
    }

    @Override
    public boolean update(Duck duck) {
        return dao.update(duck);
    }

    @Override
    public boolean unregisterById(int id) {
        return dao.deleteById(id);
    }
}

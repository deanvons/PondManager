package no.loopacademy.HelloSpringExperiments.DataAccess;

import no.loopacademy.HelloSpringExperiments.Entities.Duck;

import java.util.List;
import java.util.Optional;

public interface DuckDataAccessOption {

    public List<Duck> selectAll();

    public Optional<Duck> selectById(int id);

    public Duck insert(Duck duck);

    public boolean update(Duck duck);

    public boolean deleteById(int id);
}

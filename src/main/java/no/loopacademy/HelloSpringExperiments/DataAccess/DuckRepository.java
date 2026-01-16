package no.loopacademy.HelloSpringExperiments.DataAccess;

import no.loopacademy.HelloSpringExperiments.Entities.Duck;

import java.util.List;
import java.util.Optional;

public interface DuckRepository{

    // here we create a layer that is more intuitive and represents Duck storage less technical and more in line with the domain
    List<Duck> getAll();
    Optional<Duck> getById(int id);
    Duck register(Duck duck);
    boolean update(Duck duck);
    boolean unregisterById(int id);



}

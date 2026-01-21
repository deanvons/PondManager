package no.loopacademy.HelloSpringExperiments.DataAccess;

import no.loopacademy.HelloSpringExperiments.Models.Duck;

public interface DataAccessCRUD
{
    public void findDucks();
    public Duck goFindARandomDuck();
    public Duck hatchADuck(Duck duck);
    public void ChangeADuck();
}

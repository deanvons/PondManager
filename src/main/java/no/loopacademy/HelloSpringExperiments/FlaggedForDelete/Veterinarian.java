package no.loopacademy.HelloSpringExperiments.FlaggedForDelete;

import no.loopacademy.HelloSpringExperiments.SampleDependencies.Duck;

// REMOVE
public class Veterinarian {

    // This class is redundant
    // We don't care about variable Vets
    // We care about health checks

    private Duck duck;
    public Veterinarian(Duck duck){
        this.duck = duck;
    }

    public void checkForHealthyQuack(){
        duck.quack();
    }

}

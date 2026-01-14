package no.loopacademy.HelloSpringExperiments.SampleDependencies;

import org.springframework.stereotype.Component;

// Spring manages this classes object creation
@Component
public class Veterinarian {

    private Duck duck;
    public Veterinarian(Duck duck){
        this.duck = duck;
    }

    public void checkForHealthyQuack(){
        duck.quack();
    }




}

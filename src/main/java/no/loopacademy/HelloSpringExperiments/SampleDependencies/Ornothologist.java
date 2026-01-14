package no.loopacademy.HelloSpringExperiments.SampleDependencies;

import org.springframework.stereotype.Component;

// Spring manages this classes object creation
@Component
public class Ornothologist {

    // provided by spring DI
    private Duck duck;
    public Ornothologist(Duck duck){
        this.duck = duck;
    }

    public void confirmValidDuckObject(){
        duck.quack();
    }


}

package no.loopacademy.HelloSpringExperiments.SampleDependencies;

import org.springframework.stereotype.Component;


public class Ornothologist {

    // provided by spring DI
    private Duck duck;
    public Ornothologist(Duck duck){
        this.duck = duck;
    }

    public Ornothologist(){

    }

    public void ConfirmValidDuckObject(){
        duck.quack();
    }


}

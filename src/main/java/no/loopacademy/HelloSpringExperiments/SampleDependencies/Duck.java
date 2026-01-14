package no.loopacademy.HelloSpringExperiments.SampleDependencies;

import org.springframework.stereotype.Component;

// Spring manages this classes object creation
@Component
public class Duck {

    public void quack(){
        System.out.println("ahh quack");
    }


}

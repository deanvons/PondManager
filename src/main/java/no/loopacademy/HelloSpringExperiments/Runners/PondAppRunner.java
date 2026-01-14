package no.loopacademy.HelloSpringExperiments.Runners;

import no.loopacademy.HelloSpringExperiments.SampleDependencies.Ornothologist;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


public class PondAppRunner implements ApplicationRunner {



    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running......");

//        The rule of thumb
//        If an object (like Ornothologist) has dependencies that you expect Spring to provide (like Duck), then that object also needs to be created by Spring, otherwise Spring never gets a chance to inject anything.


        //Ornothologist orno = new Ornothologist(); // we are trying to be responsible for it here, if that is the case then we must provide the Duck object



        orno.ConfirmValidDuckObject();





    }
}

package no.loopacademy.HelloSpringExperiments.Runners;

import no.loopacademy.HelloSpringExperiments.SampleDependencies.Duck;
import no.loopacademy.HelloSpringExperiments.SampleDependencies.Ornothologist;
import no.loopacademy.HelloSpringExperiments.SampleDependencies.Veterinarian;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Spring doesn’t scan for interfaces — it scans for beans. We must tell Spring that we have a runner for it to run for us
// Also ensure you are storing classes you want Spring to scan in the same package as the spring applcation main
@Component
public class PondAppRunner implements ApplicationRunner {
    private final Ornothologist orno;
    private  final Veterinarian vet;

    public PondAppRunner(Ornothologist orno,Veterinarian vet){
        this.orno = orno;
        this.vet = vet;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running......let's do some duck stuff");

        // The rule of thumb
        // If an object (like Ornothologist) has dependencies that you expect Spring to provide (like Duck), then that object also needs to be created by Spring,
        // otherwise Spring never gets a chance to inject anything.

        // Duck duck = new Duck(); // 1) herein lies the problem, becomes a problem when this class is required elsewhere too
        // = new Ornothologist(duck); // we are trying to be responsible for it here, if that is the case then we must provide the Duck object
        orno.ConfirmValidDuckObject();

        // maybe tempting to do this to bypass springs factories
        // Ornothologist ornoNoDuck = new Ornothologist(null); // we are trying to be responsible for it here, if that is the case then we must provide the Duck object
        // orno.ConfirmValidDuckObject(); // null reference 💀

        // Duck duckForVet = new Duck(); // 1) problem, do we need the same duck or what? We need to consider creation responsibility when deciding on using DI
        // Veterinarian vet = new Veterinarian(duckForVet); // we are trying to be responsible for it here, if that is the case then we must provide the Duck object
        vet.checkForHealthyQuack();

    }
}

package no.loopacademy.HelloSpringExperiments.Runners;

import no.loopacademy.HelloSpringExperiments.DataAccess.DuckDataAccess;
import no.loopacademy.HelloSpringExperiments.Entities.Duck;
import no.loopacademy.HelloSpringExperiments.Entities.User;
import no.loopacademy.HelloSpringExperiments.FlaggedForDelete.Veterinarian;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Spring doesn’t scan for interfaces — it scans for beans. We must tell Spring that we have a runner for it to run for us
// Also ensure you are storing classes you want Spring to scan in the same package as the spring applcation main
@Component
public class PondAppRunner implements ApplicationRunner {
//    private final User orno;
//
//    public PondAppRunner(User orno, Veterinarian vet){
//        this.orno = orno;
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running......let's do some duck stuff");

        // The rule of thumb
        // If an object (like Ornothologist) has dependencies that you expect Spring to provide (like Duck), then that object also needs to be created by Spring,
        // otherwise Spring never gets a chance to inject anything.

        // How to decide?
        // Spring beans are primarily about centrally managing and distributing shared objects (usually singletons).
        // Anything that is not shared or long-lived (pure domain objects or many instances) shifts creation responsibility to the developer — often via factories.

        // Need to make some decisions -> what is are the requirements of this application
        // also need to move away from thinking in terms of object behavior and rather application behavior (storing information) -> objects represent and facilitate
        // Ornithologist is studying Ducks -> needs to store info about them and their behaviour
        // Also has some functional requirements
        // - Look for signs of disease
        // - Send ducks in for diagnosis or quarantine
        // we will start to see the boundaries of the system and our toy OOP case

        // The application actually just runs methods that alter entity state via a DB, based on user interaction

        // Check Duck list -> read from DB but what does the table look like

        // Add a duck


        DuckDataAccess dao = new DuckDataAccess();

        var duckList = dao.findAll();

        System.out.println(duckList.get(1).getNickName());


    }
}

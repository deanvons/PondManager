package no.loopacademy.HelloSpringExperiments.Runners;


import no.loopacademy.HelloSpringExperiments.DataAccess.DuckRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Spring doesn’t scan for interfaces — it scans for beans. We must tell Spring that we have a runner for it to run for us
// Also ensure you are storing classes you want Spring to scan in the same package as the spring applcation main
@Component
public class PondAppRunner implements ApplicationRunner {
    private final DuckRepository duckRepository;

    public PondAppRunner(DuckRepository duckRepository){
        this.duckRepository = duckRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running......let's do some duck stuff");

        // The application actually just runs methods that alter entity state via a DB, based on user interaction

        // Check Duck list -> read from DB but what does the table look like

        // now we can interact with the duck data in a way what feels like a collection
        var duckList = duckRepository.getAll();

        duckList.forEach(duck ->  System.out.println(duck.getNickName()));



    }
}

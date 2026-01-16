package no.loopacademy.HelloSpringExperiments.Runners;


import no.loopacademy.HelloSpringExperiments.DataAccess.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Spring doesn’t scan for interfaces — it scans for beans. We must tell Spring that we have a runner for it to run for us
// Also ensure you are storing classes you want Spring to scan in the same package as the spring applcation main
@Component
public class PondAppRunner implements ApplicationRunner {
    private final DuckService duckService;

    public PondAppRunner(DuckService duckService){
        this.duckService = duckService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running......let's do some duck stuff");

        // The application actually just runs methods that alter entity state via a DB, based on user interaction

        // Check Duck list -> read from DB but what does the table look like

        // now we can interact with the duck data in a way what feels like a business process
        var duckList = duckService.listDucks();

        duckList.forEach(duck ->  System.out.println(duck.getNickName()));

        // this area of our application is essentially where we decide what code to run based on the user needs / input

        // business rules exist only in the services

        // We need a class/layer dedicated to buser responses (CONTROLLER)

    }
}

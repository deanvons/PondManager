package no.loopacademy.HelloSpringExperiments.Runners;


import no.loopacademy.HelloSpringExperiments.DataAccess.DuckDataAccessOption;
import no.loopacademy.HelloSpringExperiments.DataAccess.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Entities.Duck;
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

        // Add a duck

//        Duck freshQuacker = new Duck();
//        freshQuacker.setNickName("Jimmy");
//        freshQuacker.setAge(2);
//        freshQuacker.setWeight(5.3);
//
//        dao.insert(freshQuacker);

        // Show all ducks

        var duckList = duckRepository.getAll();

        duckList.forEach(duck ->  System.out.println(duck.getNickName()));



    }
}

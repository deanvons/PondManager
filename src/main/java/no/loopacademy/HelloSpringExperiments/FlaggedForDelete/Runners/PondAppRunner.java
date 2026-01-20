package no.loopacademy.HelloSpringExperiments.FlaggedForDelete.Runners;


import no.loopacademy.HelloSpringExperiments.Controllers.DuckConsoleController;
import no.loopacademy.HelloSpringExperiments.DataAccess.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Spring doesn’t scan for interfaces — it scans for beans. We must tell Spring that we have a runner for it to run for us
// Also ensure you are storing classes you want Spring to scan in the same package as the spring applcation main
@Component
public class PondAppRunner implements ApplicationRunner {
    private final DuckConsoleController duckConsoleController;

    public PondAppRunner(DuckConsoleController duckConsoleController){
        this.duckConsoleController = duckConsoleController;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Pond somePond = new Pond();

        //somePond.Ducks.for //
    }
}

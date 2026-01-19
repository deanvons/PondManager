package no.loopacademy.HelloSpringExperiments.Controllers;


import no.loopacademy.HelloSpringExperiments.Entities.Duck;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DuckRestController {
    // same service as before, we are only changing the distribution layer
    private DuckService service;
    public DuckRestController(DuckService duckService){
        this.service  = duckService;
    }

    // uri GET https:localhost:8080/ducks
    @GetMapping("/ducks")
    public List<Duck> getDucks() {
        var ducks = service.listDucks();
        return ducks;
    }



}

package no.loopacademy.HelloSpringExperiments.Controllers;


import no.loopacademy.HelloSpringExperiments.Entities.Duck;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DuckRestController {

    // todos
    // error handling
    // DTOs
    // path variables
    // mvc mock tests
    // db interaction tests
    // seeding
    // hibernate
    //

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

    @GetMapping("/http/sample/{message}")
    public ResponseEntity<Void> getSampleHTTPResponse (@PathVariable String message){

        return ResponseEntity.ok().build();
    }

    // uri GET https:localhost:8080/ducks/4
    @GetMapping("/ducks/{id}")
    public ResponseEntity<Duck> getDuckById(@PathVariable int id) {
        var duck = service.getDuck(id);

        return ResponseEntity.ok(duck);
    }



}

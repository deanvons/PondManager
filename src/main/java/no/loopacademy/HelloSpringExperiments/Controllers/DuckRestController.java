package no.loopacademy.HelloSpringExperiments.Controllers;



import no.loopacademy.HelloSpringExperiments.DTOs.DuckReadDTO;
import no.loopacademy.HelloSpringExperiments.Mappers.DuckMapper;
import no.loopacademy.HelloSpringExperiments.Models.Duck;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DuckRestController {

    // todos current priority
    // [x] - remaining endpoints
    // [x] - error handling
    // [|] - DTOs
    // [] - mvc mock tests
    // [] - db interaction tests
    // [] - env and config demo


    // same service as before, we are only changing the distribution layer
    private DuckService service;

    private DuckMapper duckMapper;
    public DuckRestController(DuckService duckService, DuckMapper duckMapper){
        this.service  = duckService;
        this.duckMapper = duckMapper;
    }

    @GetMapping("/livetest")
    public ResponseEntity<String> getTest (){
       return ResponseEntity.ok("LIVE");
    }

    // uri GET https:localhost:8080/ducks
    @GetMapping("/ducks")
    public List<Duck> getDucks() {
        var ducks = service.listDucks();
        return ducks;
    }

    @GetMapping("/ducks/models/{id}")
    public ResponseEntity<Duck> getDuckModelById (@PathVariable Integer id){
        var modelDuck = service.getDuck(id);
        return ResponseEntity.ok(modelDuck);
    }
    // GET /ducks/{id}
    @GetMapping("/ducks/{id}")
    public ResponseEntity<Duck> getDuckById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getDuck(id));
    }

    // POST /ducks
    @PostMapping("/ducks")
    public ResponseEntity<Duck> createDuck(@RequestBody Duck duck) {
        Duck saved = service.registerDuck(duck);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /ducks/{id}
    @PutMapping("/ducks/{id}")
    public ResponseEntity<Duck> updateDuck(@PathVariable Integer id, @RequestBody Duck duck) {
        Duck updated = service.updateDuck(id, duck);
        return ResponseEntity.ok(updated);
    }

    // DELETE /ducks/{id}
    @DeleteMapping("/ducks/{id}")
    public ResponseEntity<Void> deleteDuck(@PathVariable Integer id) {
        service.removeDuck(id);
        return ResponseEntity.noContent().build();
    }



}

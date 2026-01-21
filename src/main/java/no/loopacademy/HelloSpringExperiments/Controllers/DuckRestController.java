package no.loopacademy.HelloSpringExperiments.Controllers;


import no.loopacademy.HelloSpringExperiments.DTOConverters.DuckDTOConverter;
import no.loopacademy.HelloSpringExperiments.DTOs.DuckReadDTO;
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

    // todos current priority'
    // [x] - remaining endpoints
    // [x] - error handling
    // [] - automate DTOs
    // [] - mvc mock tests
    // [] - db interaction tests


    // same service as before, we are only changing the distribution layer
    private DuckService service;
    private DuckDTOConverter converter;
    public DuckRestController(DuckService duckService, DuckDTOConverter converter){
        this.service  = duckService;
        this.converter = converter;
    }


    // these just illustrate path variables, infinite referenceing etc
    @GetMapping("/ducks/models/{id}")
    public ResponseEntity<Duck> getDuckModelById (@PathVariable Integer id){
//        if(message == "secret") -> doesnt compare the value it compares the
        var modelDuck = service.getDuck(id);

        return ResponseEntity.ok(modelDuck);
    }

    @GetMapping("/sampleLoadedDuck")
    public ResponseEntity<Duck> getDuckModelLoaded (){

        Pond pond = new Pond();
        pond.setId(1);
        pond.setLocation("Løkka");
        pond.setName("Junky Bridge");

        var modelDuck = new Duck();
        modelDuck.setId(2);
        modelDuck.setAge(4);
        modelDuck.setNickName("Jeff");
        modelDuck.setWeight(43.2);
        modelDuck.setPondId(1);
        modelDuck.setPond(pond);

        List<Duck> ducks = new ArrayList<>();
        ducks.add(modelDuck);

        pond.setDucks(ducks);

        return ResponseEntity.ok(modelDuck);
    }

    @GetMapping("/ducks/DTO/{id}")
    public ResponseEntity<DuckReadDTO> getDTODuckById (@PathVariable Integer id){
//        if(message == "secret") -> doesnt compare the value it compares the
        var modelDuck = service.getDuck(id);

        var duckReadDto = converter.duckToReadDTO(modelDuck);

        return ResponseEntity.ok(duckReadDto);
    }

    @GetMapping("/http/sampleresponse")
    public ResponseEntity<Void> getSampleHTTPResponseNoInput (){
//        if(message == "secret") -> doesnt compare the value it compares the
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/http/sample/{message}")
    public ResponseEntity<Void> getSampleHTTPResponse (@PathVariable String message){
//        if(message == "secret") -> doesnt compare the value it compares the
        if ("secret".equals(message))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/http/sample")
    public ResponseEntity<Void> getSampleHTTPResponseWithRequestParam (@RequestParam String name){
//        if(message == "secret") -> doesnt compare the value it compares the
        if ("dean".equals(name))
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.badRequest().build();
    }


    // these are our actual endpoints

    // uri GET https:localhost:8080/ducks
    @GetMapping("/ducks")
    public List<Duck> getDucks() {
        var ducks = service.listDucks();
        return ducks;
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

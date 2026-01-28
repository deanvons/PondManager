package no.loopacademy.HelloSpringExperiments.Controllers;

import no.loopacademy.HelloSpringExperiments.Models.Pond;
import no.loopacademy.HelloSpringExperiments.Services.PondService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ponds")
public class PondController {

    private final PondService pondService;

    public PondController(PondService pondService) {
        this.pondService = pondService;
    }

    @GetMapping
    public List<Pond> getAll() {
        return pondService.getAll();
    }

    @GetMapping("/{id}")
    public Pond getById(@PathVariable int id) {
        return pondService.getById(id);
    }

    @PostMapping
    public Pond create(@RequestBody Pond pond) {
        return pondService.create(pond);
    }

    @PutMapping("/{id}")
    public Pond update(@PathVariable int id, @RequestBody Pond pond) {
        return pondService.update(id, pond);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        pondService.delete(id);
    }
}

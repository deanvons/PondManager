package no.loopacademy.HelloSpringExperiments.Controllers;

import no.loopacademy.HelloSpringExperiments.Models.Worbler;
import no.loopacademy.HelloSpringExperiments.Services.WorblerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worblers")
public class WorblerController {

    private final WorblerService worblerService;

    public WorblerController(WorblerService worblerService) {
        this.worblerService = worblerService;
    }

    @GetMapping
    public List<Worbler> getAll() {
        return worblerService.getAll();
    }

    @GetMapping("/{id}")
    public Worbler getById(@PathVariable int id) {
        return worblerService.getById(id);
    }

    @PostMapping
    public Worbler create(@RequestBody Worbler worbler) {
        return worblerService.create(worbler);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        worblerService.delete(id);
    }

    // --- Relationship endpoints (M:M) ---

    @PostMapping("/{worblerId}/ponds/{pondId}")
    public Worbler assignToPond(@PathVariable int worblerId, @PathVariable int pondId) {
        return worblerService.assignToPond(worblerId, pondId);
    }

    @DeleteMapping("/{worblerId}/ponds/{pondId}")
    public Worbler removeFromPond(@PathVariable int worblerId, @PathVariable int pondId) {
        return worblerService.removeFromPond(worblerId, pondId);
    }
}

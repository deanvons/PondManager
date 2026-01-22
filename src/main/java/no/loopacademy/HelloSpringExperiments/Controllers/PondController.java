package no.loopacademy.HelloSpringExperiments.Controllers;

import no.loopacademy.HelloSpringExperiments.DTOs.DuckReadDTO;
import no.loopacademy.HelloSpringExperiments.DTOs.PondReadDTO;
import no.loopacademy.HelloSpringExperiments.Mappers.PondMapper;
import no.loopacademy.HelloSpringExperiments.Models.Duck;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PondController {

    private DuckService duckService;
    private PondMapper pondMapper;

    public PondController(DuckService duckService, PondMapper pondMapper) {
        this.duckService = duckService;
        this.pondMapper = pondMapper;
    }

    @GetMapping("/ponds/DTO/{id}")
    public ResponseEntity<PondReadDTO> getDTODuckById (@PathVariable Integer id){
//        if(message == "secret") -> doesnt compare the value it compares the
        var experimentalModelPond = new Pond();

        var ducks = duckService.listDucks();

        experimentalModelPond.setId(5);
        experimentalModelPond.setName("ExpermentPond");
        experimentalModelPond.setLocation("ExperimentVei x");
        experimentalModelPond.setDucks(ducks);

        var pondReadDto = pondMapper.toReadDTO(experimentalModelPond);

        return ResponseEntity.ok(pondReadDto);
    }
}

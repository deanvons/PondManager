package no.loopacademy.HelloSpringExperiments.DTOConverters;

import no.loopacademy.HelloSpringExperiments.DTOs.DuckReadDTO;
import no.loopacademy.HelloSpringExperiments.Models.Duck;
import org.springframework.stereotype.Component;

@Component
public class DuckDTOConverter {


    public DuckReadDTO duckToReadDTO (Duck modelDuck){

        DuckReadDTO duckReadDTO = new DuckReadDTO();

        duckReadDTO.setId(modelDuck.getId());
        duckReadDTO.setNickName(modelDuck.getNickName());
        duckReadDTO.setAge(modelDuck.getAge());
        duckReadDTO.setPondId(modelDuck.getPondId());

        return duckReadDTO;

    }






}

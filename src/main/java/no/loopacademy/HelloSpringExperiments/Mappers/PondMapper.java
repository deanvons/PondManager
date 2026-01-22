package no.loopacademy.HelloSpringExperiments.Mappers;

import no.loopacademy.HelloSpringExperiments.DTOs.PondReadDTO;
import no.loopacademy.HelloSpringExperiments.Models.Duck;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PondMapper {

    // source is inferred from Duck getter, target is inferred from target setter
    @Mapping(source = "duckObjects", target = "duckIds", qualifiedByName = "duckListToIds")
    PondReadDTO toReadDTO(Pond pond);

    // --- helpers ----------------------------------------------------

    @Named("duckListToIds")
    default List<Integer> duckListToIds(List<Duck> ducks) {
        // source
        if (ducks == null) return null;

        // target
        List<Integer> duckIds = new ArrayList<Integer>();

        for (int i = 0; i < ducks.size(); i++) {
            Duck duck = ducks.get(i);

            Integer id = duck.getId();

            duckIds.add(id);
        }

        return duckIds;

    }

//        return ducks.stream()
//                .map(Duck::getId)
//                .collect(Collectors.toList());
    //}
}

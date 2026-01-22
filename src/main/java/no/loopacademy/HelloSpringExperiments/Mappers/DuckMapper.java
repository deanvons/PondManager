package no.loopacademy.HelloSpringExperiments.Mappers;

import no.loopacademy.HelloSpringExperiments.DTOs.DuckReadDTO;
import no.loopacademy.HelloSpringExperiments.Models.Duck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DuckMapper {

    // Duck -> DuckReadDTO
    DuckReadDTO toReadDTO(Duck duck);

    // DuckReadDTO -> Duck (note: weight not present in DTO, so it stays null)
    Duck fromReadDTO(DuckReadDTO dto);

}
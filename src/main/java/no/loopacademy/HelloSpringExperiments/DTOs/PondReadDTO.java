package no.loopacademy.HelloSpringExperiments.DTOs;

import no.loopacademy.HelloSpringExperiments.Models.Duck;

import java.util.List;

public class PondReadDTO {

    private Integer Id;
    private String name;
    private String location;

    // navigation property
    private List<Integer> duckIds;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Integer> getDucks() {
        return duckIds;
    }


    public void setDuckIds(List<Integer> duckids) {
        this.duckIds = duckids;
    }
}

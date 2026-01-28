package no.loopacademy.HelloSpringExperiments.Models;

import jakarta.persistence.*;
import java.util.List;



    @Entity
    @Table(name = "pond")
    public class Pond {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer Id;

        @Column(name = "name")
        private String name;

        @Column(name = "location")
        private String location;

        // inverse side of Duck -> Pond
        // Duck owns the relationship via pond_id
        @OneToMany(mappedBy = "pond", fetch = FetchType.LAZY)
        private List<Duck> duckObjects;

        public Pond() {}

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

        // MapStruct-safe: uses getters/setters, not fields
        public List<Duck> getDuckObjects() {
            return duckObjects;
        }

        public void setDucks(List<Duck> ducks) {
            this.duckObjects = ducks;
        }
}

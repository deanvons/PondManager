package no.loopacademy.HelloSpringExperiments.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "worbler")
public class Worbler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "pond_worbler",
            joinColumns = @JoinColumn(name = "worbler_id"),
            inverseJoinColumns = @JoinColumn(name = "pond_id")
    )
    private List<Pond> ponds;

    public Worbler() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pond> getPonds() {
        return ponds;
    }

    public void setPonds(List<Pond> ponds) {
        this.ponds = ponds;
    }
}

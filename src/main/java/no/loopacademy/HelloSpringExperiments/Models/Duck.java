package no.loopacademy.HelloSpringExperiments.Models;
import jakarta.persistence.*;

// Spring no longer manages this classes object creation. It is a pure value object / entity
@Entity
@Table(name = "duck")
public class Duck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Double weight;

    // fk column (JDBC-friendly)
    @Column(name = "pond_id")
    private Integer PondId;

    // navigation property (ORM-friendly)
    // mapped to SAME column, but read-only so it doesn't conflict with PondId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pond_id", insertable = false, updatable = false)
    private Pond pond;

    // JPA needs a no-args constructor (you already have one implicitly)
    public Duck() {}

    public Integer getId() { return Id; }
    public void setId(Integer id) { Id = id; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Integer getPondId() { return PondId; }
    public void setPondId(Integer pondId) { PondId = pondId; }

    public Pond getPond() { return pond; }
    public void setPond(Pond pond) { this.pond = pond; }

    public void quack() {
        System.out.println(this.nickName + " totally quacked");
    }
}

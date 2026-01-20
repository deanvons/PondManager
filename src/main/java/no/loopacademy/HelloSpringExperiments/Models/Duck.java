package no.loopacademy.HelloSpringExperiments.Models;

// Spring no longer manages this classes object creation. It is a pure value object / entity
public class Duck {

    private Integer Id;
    private String nickName;
    private Integer age;
    private Double weight;

    // fk for pond
    private Integer PondId;

    // navigation property
    private Pond pond;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    // this is no longer needed or needs to be adapted for information updating
    // The entity/value object is responsible for its own state + invariants only.
    public void quack(){
        System.out.println(this.nickName + " totally quacked");
    }

    public Integer getPondId() {
        return PondId;
    }

    public void setPondId(Integer pondId) {
        PondId = pondId;
    }

    public Pond getPond() {
        return pond;
    }

    public void setPond(Pond pond) {
        this.pond = pond;
    }
}

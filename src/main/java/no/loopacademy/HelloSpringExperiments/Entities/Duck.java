package no.loopacademy.HelloSpringExperiments.Entities;

// Spring no longer manages this classes object creation. It is a pure value object / entity
public class Duck {

    private Integer Id;
    private String nickName;
    private Integer age;
    private Double weight;


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
}

package no.loopacademy.HelloSpringExperiments.DTOs;

public class DuckReadDTO {

    private Integer Id;
    private String nickName;
    private Integer age;
    // private Double weight; excluded to avoid fat shaming
    private Integer PondId;

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

    public Integer getPondId() {
        return PondId;
    }

    public void setPondId(Integer pondId) {
        PondId = pondId;
    }
}

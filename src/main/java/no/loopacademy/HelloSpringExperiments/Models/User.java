package no.loopacademy.HelloSpringExperiments.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orno_certificate_code", length = 100)
    private String ornoCertificateCode;

    @Column(name = "years_experience")
    private Integer yearsExperience;

    // FK-ish reference to JWT subject (not a real FK usually)
    @Column(name = "jwt_sub", unique = true, nullable = false, length = 120)
    private String jwtSub;

    public User() {}

    public User(String ornoCertificateCode, Integer yearsExperience, String jwtSub) {
        this.ornoCertificateCode = ornoCertificateCode;
        this.yearsExperience = yearsExperience;
        this.jwtSub = jwtSub;
    }

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrnoCertificateCode() {
        return ornoCertificateCode;
    }

    public void setOrnoCertificateCode(String ornoCertificateCode) {
        this.ornoCertificateCode = ornoCertificateCode;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public String getJwtSub() {
        return jwtSub;
    }

    public void setJwtSub(String jwtSub) {
        this.jwtSub = jwtSub;
    }
}

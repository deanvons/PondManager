package no.loopacademy.HelloSpringExperiments.Models;

public class User {

    // This class represents the user
    // The user/ornothogist interacts with the information about ducks
    // not the ducks themselves
    // What does the system actually need to store about the ornothologist
    // Likely 1) UserId, 2) username, 3) email ....
    // Thus this class is just an entity
    // The objects that act on Duck data is what we need to focus on

    private Integer Id;
    private String userName;
    private String emailAddress;

}

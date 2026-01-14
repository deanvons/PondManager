package no.loopacademy.HelloSpringExperiments.SampleDependencies;

public class Veterinarian {



    private Duck duck;
    public Veterinarian(Duck duck){
        this.duck = duck;
    }

    public void checkForHealthyQuack(){
        duck.quack();
    }




}

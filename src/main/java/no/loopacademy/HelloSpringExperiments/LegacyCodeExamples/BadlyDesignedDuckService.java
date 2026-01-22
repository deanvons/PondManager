package no.loopacademy.HelloSpringExperiments.LegacyCodeExamples;

public class BadlyDesignedDuckService {

    private DuckDataOperationsInterface dataOperations;
    public BadlyDesignedDuckService(DuckDataOperationsInterface dataOperations){
        this.dataOperations = dataOperations;
    }


    public void doDataDuckStuff(){




        dataOperations.getDucks();
    }
}

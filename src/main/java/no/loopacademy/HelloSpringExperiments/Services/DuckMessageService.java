package no.loopacademy.HelloSpringExperiments.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DuckMessageService {

    private final String message;

    public DuckMessageService(@Value("${duck.message}") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
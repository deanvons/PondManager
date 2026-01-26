package no.loopacademy.HelloSpringExperiments.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDuckException extends RuntimeException {
    public InvalidDuckException(String message) {
        super(message);
    }
}

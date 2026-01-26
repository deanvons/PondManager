package no.loopacademy.HelloSpringExperiments.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExtraFeatureService {

    private final boolean enabled;

    public ExtraFeatureService(@Value("${feature.extra.enabled}") boolean enabled) {
        this.enabled = enabled;
    }

    public String status() {
        return enabled ? "Extra feature is ON" : "Extra feature is OFF";
    }
}

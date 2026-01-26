package no.loopacademy.HelloSpringExperiments.Controllers;

import no.loopacademy.HelloSpringExperiments.Services.DuckMessageService;
import no.loopacademy.HelloSpringExperiments.Services.ExtraFeatureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveTestController {

    private final DuckMessageService duckMessageService;
    private final ExtraFeatureService extraFeatureService;

    public LiveTestController(DuckMessageService duckMessageService,
                              ExtraFeatureService extraFeatureService) {
        this.duckMessageService = duckMessageService;
        this.extraFeatureService = extraFeatureService;
    }

    @GetMapping("/livetests")
    public String livetest() {
        return duckMessageService.getMessage() + " | " + extraFeatureService.status();
    }
}
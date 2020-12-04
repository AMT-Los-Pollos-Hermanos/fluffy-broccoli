package ch.heigvd.broccoli.event;

import ch.heigvd.broccoli.rule.RuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final RuleService service;

    public EventController(RuleService service) {
        this.service = service;
    }

    @PostMapping("/events")
    void postEvent(@RequestBody EventDTO event) {
        service.process(event);
    }

}

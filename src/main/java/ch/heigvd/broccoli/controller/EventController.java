package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.event.EventDTO;
import ch.heigvd.broccoli.application.event.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping("/events")
    void postEvent(@RequestBody EventDTO event) {
        service.process(event);
    }

}

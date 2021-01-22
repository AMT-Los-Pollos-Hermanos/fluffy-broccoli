package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.event.EventDTO;
import ch.heigvd.broccoli.application.event.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Api(tags = "Events")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @ApiOperation("Post an event")
    @PostMapping("/events")
    void postEvent(@RequestBody EventDTO event) {
        Logger.getLogger("Event").info(event.toString());
        service.process(event);
    }

}

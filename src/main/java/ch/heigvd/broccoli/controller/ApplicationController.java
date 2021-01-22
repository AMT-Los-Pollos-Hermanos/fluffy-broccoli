package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.application.ApplicationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(tags = "Applications")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
public class ApplicationController {

    private final ApplicationRepository repository;

    public ApplicationController(ApplicationRepository repository) {
        this.repository = repository;
    }

    @ApiOperation("Add a new application")
    @PostMapping(value = "/applications", produces = "application/json")
    Application createNewApp(@RequestParam String name) {
        return repository.save(Application.builder().name(name).apiKey(UUID.randomUUID()).build());
    }


}

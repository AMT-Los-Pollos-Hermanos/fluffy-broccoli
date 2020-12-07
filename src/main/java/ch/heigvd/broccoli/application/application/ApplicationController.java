package ch.heigvd.broccoli.application.application;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.application.ApplicationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ApplicationController {

    private final ApplicationRepository repository;

    public ApplicationController(ApplicationRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/applications")
    Application createNewApp(@RequestParam String name) {
        return repository.save(Application.builder().name(name).apiKey(UUID.randomUUID()).build());
    }


}

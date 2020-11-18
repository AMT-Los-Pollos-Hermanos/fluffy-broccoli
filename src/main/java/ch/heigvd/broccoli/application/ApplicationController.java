package ch.heigvd.broccoli.application;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/applications")
    String get() {
        return "Hello World";
    }

    @PostMapping("/applications")
    Application createNewApp(@RequestParam String name) {
        return repository.save(Application.builder().name(name).apiKey(UUID.randomUUID()).build());
    }

}
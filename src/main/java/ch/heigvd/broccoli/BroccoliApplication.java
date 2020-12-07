package ch.heigvd.broccoli;

import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.application.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class BroccoliApplication {

    private static final Logger log = LoggerFactory.getLogger(BroccoliApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BroccoliApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ApplicationRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(Application.builder()
                    .name("My test app")
                    .apiKey(UUID.fromString("88980fa7-7167-46d5-bbe7-367a204b7bd2"))
                    .build()));
        };
    }

}

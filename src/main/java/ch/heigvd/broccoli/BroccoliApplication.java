package ch.heigvd.broccoli;

import ch.heigvd.broccoli.badge.Badge;
import ch.heigvd.broccoli.badge.BadgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BroccoliApplication {

    private static final Logger log = LoggerFactory.getLogger(BroccoliApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BroccoliApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(BadgeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(Badge.builder()
                    .name("Mon premier badge")
                    .description("La description du premier badge")
                    .icon("/image/truc/badge1.png")
                    .build()));
            log.info("Preloading " + repository.save(Badge.builder()
                    .name("Mon deuxième badge")
                    .description("La description du deuxième badge")
                    .icon("/image/truc/badge2.png")
                    .build()));
        };
    }

}

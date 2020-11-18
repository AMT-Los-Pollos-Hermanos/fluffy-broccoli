package ch.heigvd.broccoli.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application findByApiKey(UUID apiKey);

}

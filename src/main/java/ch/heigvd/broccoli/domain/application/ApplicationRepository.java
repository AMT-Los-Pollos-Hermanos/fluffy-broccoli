package ch.heigvd.broccoli.domain.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application findByApiKey(UUID apiKey);

}

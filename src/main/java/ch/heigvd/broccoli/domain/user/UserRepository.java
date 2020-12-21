package ch.heigvd.broccoli.domain.user;

import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllByApplication(Application application);
    Optional<UserEntity> findByIdAndApplication(UUID id, Application application);
}

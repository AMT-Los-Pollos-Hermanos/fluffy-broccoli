package ch.heigvd.broccoli.domain.user;

import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByApplication(Application application);
    Optional<User> findByIdAndApplication(Long id, Application application);
}

package ch.heigvd.broccoli.domain.user;

import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByApplication(Application application);
}

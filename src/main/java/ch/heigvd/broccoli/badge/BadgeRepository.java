package ch.heigvd.broccoli.badge;

import ch.heigvd.broccoli.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findAllByApplication(Application application);

}

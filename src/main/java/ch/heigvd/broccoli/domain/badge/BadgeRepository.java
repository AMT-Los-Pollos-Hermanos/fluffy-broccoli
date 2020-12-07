package ch.heigvd.broccoli.domain.badge;

import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findAllByApplication(Application application);
    Optional<Badge> findByIdAndApplication(Long id, Application application);

}

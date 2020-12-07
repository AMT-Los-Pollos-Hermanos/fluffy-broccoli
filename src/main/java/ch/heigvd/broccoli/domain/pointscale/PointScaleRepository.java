package ch.heigvd.broccoli.domain.pointscale;

import ch.heigvd.broccoli.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointScaleRepository extends JpaRepository<PointScale, Long> {

    List<PointScale> findAllByApplication(Application application);

}

package ch.heigvd.broccoli.pointscale;

import ch.heigvd.broccoli.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointScaleRepository extends JpaRepository<PointScale, Long> {

    List<PointScale> findAllByApplication(Application application);

}

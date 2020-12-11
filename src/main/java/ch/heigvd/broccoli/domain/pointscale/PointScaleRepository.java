package ch.heigvd.broccoli.domain.pointscale;

import ch.heigvd.broccoli.domain.BaseRepository;
import ch.heigvd.broccoli.domain.application.Application;

import java.util.List;

public interface PointScaleRepository extends BaseRepository<PointScale> {

    List<PointScale> findAllByApplication(Application application);

}

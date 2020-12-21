package ch.heigvd.broccoli.domain.userreceivepoint;

import ch.heigvd.broccoli.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReceivePointRepository extends JpaRepository<UserReceivePoint, Long> {

    List<UserReceivePoint> findAllByUserEntity(UserEntity userEntity);

    List<UserReceivePoint> findByUserEntityAndPointScaleIdOrderByTimestampDesc(UserEntity userEntity, Long pointScaleId);
}

package ch.heigvd.broccoli.domain.userpointscale;

import ch.heigvd.broccoli.domain.pointscale.PointScale;
import ch.heigvd.broccoli.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReceivePointRepository extends JpaRepository<UserReceivePoint, Long> {

    List<UserReceivePoint> findAllByUser(User user);

    List<UserReceivePoint> findByUserAndPointScale(User user, PointScale pointScale);
}

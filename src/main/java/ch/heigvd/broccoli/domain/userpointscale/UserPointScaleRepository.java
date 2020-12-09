package ch.heigvd.broccoli.domain.userpointscale;

import ch.heigvd.broccoli.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPointScaleRepository extends JpaRepository<UserPointScale, Long> {
    List<UserPointScale> findAllByUser(User user);
}

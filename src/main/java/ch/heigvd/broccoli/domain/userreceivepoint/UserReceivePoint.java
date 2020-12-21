package ch.heigvd.broccoli.domain.userreceivepoint;

import ch.heigvd.broccoli.domain.pointscale.PointScale;
import ch.heigvd.broccoli.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReceivePoint {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private PointScale pointScale;

    private double points;

    private Timestamp timestamp;

}

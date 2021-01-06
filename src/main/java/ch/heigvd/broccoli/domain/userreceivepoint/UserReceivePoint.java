package ch.heigvd.broccoli.domain.userreceivepoint;

import ch.heigvd.broccoli.domain.pointscale.PointScale;
import ch.heigvd.broccoli.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReceivePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private PointScale pointScale;

    private Integer points;

    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = Date.from(Instant.now());

}

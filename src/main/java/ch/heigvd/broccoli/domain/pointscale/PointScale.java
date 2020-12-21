package ch.heigvd.broccoli.domain.pointscale;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.userreceivepoint.UserReceivePoint;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointScale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Application application;

    @OneToMany(
            mappedBy = "pointScale",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    @Builder.Default
    private List<UserReceivePoint> userReceivePoints = new ArrayList<>();

}

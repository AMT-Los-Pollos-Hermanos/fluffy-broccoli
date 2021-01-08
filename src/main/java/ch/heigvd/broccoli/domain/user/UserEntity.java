package ch.heigvd.broccoli.domain.user;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.badge.Badge;
import ch.heigvd.broccoli.domain.userreceivepoint.UserReceivePoint;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    @ManyToOne
    private Application application;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    private List<Badge> badges = new ArrayList<>();

    @OneToMany(
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    @ToString.Exclude
    private List<UserReceivePoint> userReceivePoints = new ArrayList<>();

}

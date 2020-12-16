package ch.heigvd.broccoli.domain.user;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.badge.Badge;
import ch.heigvd.broccoli.domain.userpointscale.UserReceivePoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue
    private long id;

    private String firstname;

    private String lastname;

    private String username;

    @ManyToOne
    private Application application;

    @ManyToMany
    private List<Badge> badges;

    @OneToMany
    private List<UserReceivePoint> userReceivePoints;
}

package ch.heigvd.broccoli.domain.badge;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Badge {

    @Id @GeneratedValue
    private long id;

    private String name;

    private String description;

    private String icon;

    @ManyToOne
    private Application application;

    @ManyToMany(mappedBy = "badges")
    @Builder.Default
    @ToString.Exclude
    private List<UserEntity> users = new ArrayList<>();
}

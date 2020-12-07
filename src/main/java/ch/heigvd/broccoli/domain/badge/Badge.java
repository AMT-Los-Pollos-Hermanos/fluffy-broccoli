package ch.heigvd.broccoli.domain.badge;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.user.User;
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
public class Badge {

    @Id @GeneratedValue
    private long id;

    private String name;

    private String description;

    private String icon;

    @ManyToOne
    private Application application;

    @ManyToMany
    private List<User> users;
}

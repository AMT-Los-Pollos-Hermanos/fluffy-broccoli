package ch.heigvd.broccoli.badge;

import ch.heigvd.broccoli.application.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

}

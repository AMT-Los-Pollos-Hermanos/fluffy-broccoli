package ch.heigvd.broccoli.badge;

import ch.heigvd.broccoli.application.Application;
import io.swagger.annotations.ApiModelProperty;
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
@AllArgsConstructor
@NoArgsConstructor
public class Badge {

    @Id @GeneratedValue
    @ApiModelProperty(example = "1", position = 1)
    private long id;

    @ApiModelProperty(example = "My amazing badge", position = 2)
    private String name;

    @ApiModelProperty(example = "You can get this badge after 50 comments", position = 3)
    private String description;

    @ApiModelProperty(example = "/images/icon.png", position = 4)
    private String icon;

    @ManyToOne
    private Application application;

}

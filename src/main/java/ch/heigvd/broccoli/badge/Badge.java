package ch.heigvd.broccoli.badge;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Badge {

    @Id @GeneratedValue
    @ApiModelProperty(example = "1", position = 1)
    private long id;

    @ApiModelProperty(example = "Mon super badge", position = 2)
    private String name;

    @ApiModelProperty(example = "Super badge obtenu après 50 commentaires sur notre site", position = 3)
    private String description;

    @ApiModelProperty(example = "/dossier/des/images/icon.png", position = 4)
    private String icon;

}

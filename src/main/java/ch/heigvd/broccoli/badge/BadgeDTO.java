package ch.heigvd.broccoli.badge;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
public class BadgeDTO {
    @Id @GeneratedValue
    @ApiModelProperty(example = "1", position = 1)
    private long id;

    @ApiModelProperty(example = "My amazing badge", position = 2)
    private String name;

    @ApiModelProperty(example = "You can get this badge after 50 comments", position = 3)
    private String description;

    @ApiModelProperty(example = "/images/icon.png", position = 4)
    private String icon;


}

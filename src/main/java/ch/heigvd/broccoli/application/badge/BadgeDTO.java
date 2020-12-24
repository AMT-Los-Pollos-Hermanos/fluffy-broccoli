package ch.heigvd.broccoli.application.badge;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeDTO {

    @ApiModelProperty(example = "1", position = 1)
    private long id;

    @ApiModelProperty(example = "My amazing badge", position = 2)
    private String name;

    @ApiModelProperty(example = "You can get this badge after 50 comments", position = 3)
    private String description;

    @ApiModelProperty(example = "/images/icon.png", position = 4)
    private String icon;


}

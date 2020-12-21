package ch.heigvd.broccoli.domain.award;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AwardPoint implements Award {

    @JsonProperty("point_scale_id")
    private Long pointScale;
    private Integer amount;

}

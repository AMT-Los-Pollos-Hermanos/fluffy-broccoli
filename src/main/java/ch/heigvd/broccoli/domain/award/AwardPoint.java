package ch.heigvd.broccoli.domain.award;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardPoint implements Award {

    @JsonProperty("point_scale_id")
    private Long pointScale;
    private Integer amount;

}

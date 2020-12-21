package ch.heigvd.broccoli.domain.award;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AwardBadge implements Award {

    @JsonProperty("badge_id")
    private Long badgeId;

}

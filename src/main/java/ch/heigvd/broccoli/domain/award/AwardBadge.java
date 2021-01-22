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
public class AwardBadge implements Award {

    @JsonProperty("badge_id")
    private Long badgeId;

}

package ch.heigvd.broccoli.domain.rule.specification;

import ch.heigvd.broccoli.domain.award.AwardBadge;
import ch.heigvd.broccoli.domain.award.AwardPoint;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RuleThen {

    @JsonProperty("award_points")
    private AwardPoint awardPoints;

    @JsonProperty("award_badge")
    private AwardBadge awardBadge;

}

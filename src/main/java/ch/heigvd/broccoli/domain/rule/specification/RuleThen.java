package ch.heigvd.broccoli.domain.rule.specification;

import ch.heigvd.broccoli.domain.award.AwardBadge;
import ch.heigvd.broccoli.domain.award.AwardPoint;
import lombok.Data;

@Data
public class RuleThen {

    private AwardPoint awardPoints;
    private AwardBadge awardBadge;

}

package ch.heigvd.broccoli.application.rule.specification;

import ch.heigvd.broccoli.domain.award.Award;
import lombok.Data;

import java.util.List;

@Data
public class RuleThen {

    private List<Award> awards;

}

package ch.heigvd.broccoli.rule.specification;

import ch.heigvd.broccoli.award.Award;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class RuleThen implements Serializable {

    private List<Award> awards;
}

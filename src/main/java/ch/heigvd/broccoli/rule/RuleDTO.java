package ch.heigvd.broccoli.rule;

import ch.heigvd.broccoli.rule.specification.RuleIf;
import ch.heigvd.broccoli.rule.specification.RuleThen;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleDTO {

    @JsonProperty("if")
    private RuleIf ruleIf;

    @JsonProperty("then")
    private RuleThen ruleThen;

}

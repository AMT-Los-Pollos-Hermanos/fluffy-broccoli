package ch.heigvd.broccoli.application.rule;

import ch.heigvd.broccoli.domain.rule.specification.RuleIf;
import ch.heigvd.broccoli.domain.rule.specification.RuleThen;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleDTO {

    private Long id;

    @JsonProperty("if")
    private RuleIf ruleIf;

    @JsonProperty("then")
    private RuleThen ruleThen;

}

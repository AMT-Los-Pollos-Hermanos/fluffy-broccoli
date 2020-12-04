package ch.heigvd.broccoli.rule;

import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.rule.specification.RuleIf;
import ch.heigvd.broccoli.rule.specification.RuleThen;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    @Id
    @GeneratedValue
    private long id;

    @JsonProperty("if")
    private RuleIf ruleIf;

    @JsonProperty("then")
    private RuleThen ruleThen;

    @ManyToOne
    private Application application;

}

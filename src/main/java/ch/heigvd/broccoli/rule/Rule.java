package ch.heigvd.broccoli.rule;

import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.rule.specification.RuleIf;
import ch.heigvd.broccoli.rule.specification.RuleIfConverter;
import ch.heigvd.broccoli.rule.specification.RuleThen;
import ch.heigvd.broccoli.rule.specification.RuleThenConverter;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    @Id
    @GeneratedValue
    private long id;

    @Convert(converter = RuleIfConverter.class)
    private RuleIf ruleIf;

    @Convert(converter = RuleThenConverter.class)
    private RuleThen ruleThen;

    @ManyToOne
    @NotNull
    private Application application;

}

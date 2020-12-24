package ch.heigvd.broccoli.domain.rule;

import ch.heigvd.broccoli.domain.rule.specification.RuleIf;
import ch.heigvd.broccoli.domain.rule.specification.RuleIfConverter;
import ch.heigvd.broccoli.domain.rule.specification.RuleThen;
import ch.heigvd.broccoli.domain.rule.specification.RuleThenConverter;
import ch.heigvd.broccoli.domain.application.Application;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Convert(converter = RuleIfConverter.class)
    @Column(columnDefinition = "TEXT")
    private RuleIf ruleIf;

    @Convert(converter = RuleThenConverter.class)
    @Column(columnDefinition = "TEXT")
    private RuleThen ruleThen;

    @ManyToOne
    @NotNull
    private Application application;

}

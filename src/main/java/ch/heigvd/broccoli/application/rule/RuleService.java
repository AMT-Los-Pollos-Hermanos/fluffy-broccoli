package ch.heigvd.broccoli.application.rule;

import ch.heigvd.broccoli.application.BaseService;
import ch.heigvd.broccoli.application.event.EventDTO;
import ch.heigvd.broccoli.domain.award.Award;
import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.rule.Rule;
import ch.heigvd.broccoli.domain.rule.RuleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleService extends BaseService<RuleDTO, Rule> {

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public void process(EventDTO event) {
        boolean isPropertiesMatching;
        List<Rule> rules = repository.findAllByApplication((Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        // For each rules
        for (Rule r : rules) {
            // Type must match
            if (r.getRuleIf().getType().equals(event.getType())) {

                // For each properties
                isPropertiesMatching = true;
                for (Map.Entry<String, String> property : r.getRuleIf().getProperties().entrySet()) {
                    // Property must match
                    if (!event.getProperties().containsKey(property.getKey()) || !event.getProperties().containsValue(property.getValue())) {
                        isPropertiesMatching = false;
                        break;
                    }
                }

                // Then, give award
                if (isPropertiesMatching) {
                    for (Award award : r.getRuleThen().getAwards()) {
                        award.apply();
                    }
                }
            }
        }
    }

    public RuleDTO add(RuleDTO ruleDTO) {
        return toDTO(repository.save(Rule.builder()
                .ruleIf(ruleDTO.getRuleIf())
                .ruleThen(ruleDTO.getRuleThen())
                .application(app()).build()));
    }

    public RuleDTO update(Long id, RuleDTO ruleDTO) {
        repository.findByIdAndApplication(id, app()).map(rule -> {
            rule.setRuleIf(ruleDTO.getRuleIf());
            rule.setRuleThen(ruleDTO.getRuleThen());
            return repository.save(rule);

            // TODO: Create global exception type for not found items
        }).orElseThrow(() -> new RuntimeException("Rule not found"));
        return ruleDTO;
    }


    public RuleDTO toDTO(Rule rule) {
        return RuleDTO.builder()
                .id(rule.getId())
                .ruleIf(rule.getRuleIf())
                .ruleThen(rule.getRuleThen())
                .build();
    }

}

package ch.heigvd.broccoli.rule;

import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.award.Award;
import ch.heigvd.broccoli.event.EventDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuleService {

    private final RuleRepository repository;

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

    public List<RuleDTO> all() {
        return toDTO(repository.findAll());
    }

    public RuleDTO one(Long id) {
        return toDTO(repository.findById(id));
    }

    public RuleDTO add(RuleDTO id) {
        return null;
    }

    public RuleDTO toDTO(Optional<Rule> rule) {
        return rule.map(value -> RuleDTO.builder()
                .ruleIf(value.getRuleIf())
                .ruleThen(value.getRuleThen())
                .build()).orElse(null);
    }

    public List<RuleDTO> toDTO(List<Rule> rules) {
        return rules.stream().map(rule -> RuleDTO.builder()
                .ruleIf(rule.getRuleIf())
                .ruleThen(rule.getRuleThen())
                .build()).collect(Collectors.toList());
    }

}

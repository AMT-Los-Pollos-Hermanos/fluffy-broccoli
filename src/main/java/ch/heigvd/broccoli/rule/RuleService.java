package ch.heigvd.broccoli.rule;

import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.event.EventDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuleService {

    private final RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public void process(EventDTO event) {
        List<Rule> rules = repository.findAllByApplication((Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        for (Rule r : rules) {
            if(r.getRuleIf().getType().equals(event.getType())) {
                // Do something
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

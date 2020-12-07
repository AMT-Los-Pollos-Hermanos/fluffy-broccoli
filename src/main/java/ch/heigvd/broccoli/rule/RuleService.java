package ch.heigvd.broccoli.rule;

import ch.heigvd.broccoli.ServiceInterface;
import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.event.EventDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuleService implements ServiceInterface<RuleDTO, Rule> {

    private final RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public void process(EventDTO event) {
        List<Rule> rules = repository.findAllByApplication((Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        for (Rule r : rules) {
            if (r.getRuleIf().getType().equals(event.getType())) {
                // Do something
            }
        }
    }

    public List<RuleDTO> all() {
        return toDTO(repository.findAllByApplication(app()));
    }

    public RuleDTO one(Long id) {
        return toDTO(repository.findByIdAndApplication(id, app()));
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
        }).orElseThrow(() -> new RuntimeException("Rule not found"));
        return ruleDTO;
    }

    public RuleDTO delete(Long id) {
        repository.findByIdAndApplication(id, app()).map(rule -> {
            repository.delete(rule);
            return rule;
        }).orElseThrow(() -> new RuntimeException("Rule not found"));
        return null;
    }

    public RuleDTO toDTO(Rule rule) {
        return RuleDTO.builder()
                .id(rule.getId())
                .ruleIf(rule.getRuleIf())
                .ruleThen(rule.getRuleThen())
                .build();
    }

    public RuleDTO toDTO(Optional<Rule> rule) {
        return rule.map(this::toDTO).orElse(null);
    }

    public List<RuleDTO> toDTO(List<Rule> rules) {
        return rules.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

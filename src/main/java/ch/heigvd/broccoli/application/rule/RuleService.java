package ch.heigvd.broccoli.application.rule;

import ch.heigvd.broccoli.application.BaseService;
import ch.heigvd.broccoli.application.NotFoundException;
import ch.heigvd.broccoli.domain.rule.Rule;
import ch.heigvd.broccoli.domain.rule.RuleRepository;
import org.springframework.stereotype.Service;

@Service
public class RuleService extends BaseService<RuleDTO, Rule> {

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }


    @Override
    public RuleDTO add(RuleDTO ruleDTO) {
        return toDTO(repository.save(Rule.builder()
                .ruleIf(ruleDTO.getRuleIf())
                .ruleThen(ruleDTO.getRuleThen())
                .application(app()).build()));
    }

    @Override
    public RuleDTO update(Long id, RuleDTO ruleDTO) {
        repository.findByIdAndApplication(id, app()).map(rule -> {
            rule.setRuleIf(ruleDTO.getRuleIf());
            rule.setRuleThen(ruleDTO.getRuleThen());
            return repository.save(rule);
        }).orElseThrow(() -> new NotFoundException("Rule not found"));
        return ruleDTO;
    }

    @Override
    public RuleDTO toDTO(Rule rule) {
        return RuleDTO.builder()
                .id(rule.getId())
                .ruleIf(rule.getRuleIf())
                .ruleThen(rule.getRuleThen())
                .build();
    }

}

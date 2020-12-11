package ch.heigvd.broccoli.application.event;

import ch.heigvd.broccoli.application.award.AwardService;
import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.award.Award;
import ch.heigvd.broccoli.domain.rule.Rule;
import ch.heigvd.broccoli.domain.rule.RuleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventService {

    private final RuleRepository ruleRepository;

    private final AwardService service;

    public EventService(RuleRepository ruleRepository, AwardService service) {
        this.service = service;
        this.ruleRepository = ruleRepository;
    }

    public void process(EventDTO event) {
        boolean isPropertiesMatching;
        List<Rule> rules = ruleRepository.findAllByApplication(app());
        // For each rules
        for (Rule r : rules) {
            // Type must match
            if (r.getRuleIf().getType().equals(event.getType())) {
                // For each properties
                isPropertiesMatching = false;
                for (Map.Entry<String, String> property : r.getRuleIf().getProperties().entrySet()) {
                    // Property must match
                    if (!event.getProperties().containsKey(property.getKey()) || !event.getProperties().containsValue(property.getValue())) {
                        isPropertiesMatching = false;
                        break;
                    } else {
                        isPropertiesMatching = true;
                    }
                }
                // Then, give award
                if (isPropertiesMatching) {
                    for (Award award : r.getRuleThen().getAwards()) {
                        service.apply(award, event.getUserId());
                    }
                }
            }
        }
    }

    private Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

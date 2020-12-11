package ch.heigvd.broccoli.application.event;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.rule.Rule;
import ch.heigvd.broccoli.domain.rule.RuleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    RuleRepository ruleRepository;

    public EventService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void process(EventDTO event) {
        List<Rule> rules = ruleRepository.findAllByApplication(app());
        for (Rule r : rules) {
            if (r.getRuleIf().getType().equals(event.getType())) {
                // Do something
            }
        }
    }

    private Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

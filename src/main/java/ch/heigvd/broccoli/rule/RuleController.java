package ch.heigvd.broccoli.rule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleController {

    private final RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }

    @GetMapping("/rules")
    public List<RuleDTO> all() {
        return service.all();
    }

    @GetMapping("/rules/{id}")
    public RuleDTO one(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping("/rules")
    public RuleDTO add(RuleDTO entityDto) {
        return service.add(entityDto);
    }

}

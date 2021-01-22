package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.rule.RuleDTO;
import ch.heigvd.broccoli.application.rule.RuleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public RuleDTO add(@RequestBody RuleDTO rule) {
        return service.add(rule);
    }

    @PutMapping("/rules/{id}")
    public RuleDTO update(@PathVariable Long id, @RequestBody RuleDTO rule) {
        return service.update(id, rule);
    }

    @DeleteMapping("/rules/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RuleDTO update(@PathVariable Long id) {
        return service.delete(id);
    }

}

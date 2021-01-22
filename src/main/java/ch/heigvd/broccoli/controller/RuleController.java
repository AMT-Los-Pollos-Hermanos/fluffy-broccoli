package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.rule.RuleDTO;
import ch.heigvd.broccoli.application.rule.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Rules")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
public class RuleController {

    private final RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }

    @ApiOperation("Get all rules")
    @GetMapping(value = "/rules", produces = "application/json")
    public List<RuleDTO> all() {
        return service.all();
    }

    @ApiOperation("Get only one rule")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/rules/{id}", produces = "application/json")
    public RuleDTO one(@PathVariable Long id) {
        return service.one(id);
    }

    @ApiOperation("Add a new rule")
    @PostMapping(value = "/rules", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RuleDTO add(@RequestBody RuleDTO rule) {
        return service.add(rule);
    }

    @ApiOperation("Update a rule")
    @PutMapping(value = "/rules/{id}", consumes = "application/json", produces = "application/json")
    public RuleDTO update(@PathVariable Long id, @RequestBody RuleDTO rule) {
        return service.update(id, rule);
    }

    @ApiOperation("Delete a rule")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping("/rules/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RuleDTO update(@PathVariable Long id) {
        return service.delete(id);
    }

}

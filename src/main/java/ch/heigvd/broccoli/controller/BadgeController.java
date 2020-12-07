package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.badge.BadgeDTO;
import ch.heigvd.broccoli.application.badge.BadgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Badges")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
class BadgeController {

    private final BadgeService service;

    BadgeController(BadgeService service) {
        this.service = service;
    }

    @ApiOperation("Get all badges")
    @GetMapping(value = "/badges", produces = "application/json")
    List<BadgeDTO> all() {
        return service.all();
    }

    @ApiOperation("Get only one badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/badges/{id}", produces = "application/json")
    BadgeDTO one(@PathVariable Long id) {
        return service.one(id);
    }

    @ApiOperation("Add a new badge")
    @PostMapping(value = "/badges", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    BadgeDTO newBadge(@RequestBody BadgeDTO badgeDTO) {
        return service.add(badgeDTO);
    }

    @ApiOperation("Update a badge")
    @PutMapping(value = "/badges/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<BadgeDTO> update(@RequestBody BadgeDTO newBadge, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(id, newBadge));
    }

    @ApiOperation("Delete a badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping(value = "/badges/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

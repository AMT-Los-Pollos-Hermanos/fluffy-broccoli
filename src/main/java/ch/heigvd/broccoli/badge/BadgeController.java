package ch.heigvd.broccoli.badge;

import ch.heigvd.broccoli.application.Application;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final BadgeRepository repository;

    BadgeController(BadgeRepository repository) {
        this.repository = repository;
    }

    @ApiOperation("Get all badges")
    @GetMapping(value = "/badges", produces = "application/json")
    List<BadgeDTO> all() {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BadgeService.getListDTOFromBadges(repository.findAllByApplication(app));
    }

    @ApiOperation("Get only one badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/badges/{id}", produces = "application/json")
    BadgeDTO one(@PathVariable Long id) {
        Badge badge = repository.findById(id).orElseThrow(() -> new BadgeNotFoundException(id));
        return BadgeService.getBadgeDTOFromBadge(BadgeService.authorizedBadge(badge));
    }

    @ApiOperation("Add a new badge")
    @PostMapping(value = "/badges", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    BadgeDTO newBadge(@RequestBody BadgeDTO badgeDTO) {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (app == null) {
            throw new RuntimeException("No auth principal found");
        }
        Badge badge = BadgeService.getBadgeFromDTO(badgeDTO);
        badge.setApplication(app);
        repository.save(badge);
        return badgeDTO;
    }

    @ApiOperation("Update a badge")
    @PutMapping(value = "/badges/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<BadgeDTO> update(@RequestBody BadgeDTO newBadge, @PathVariable Long id) {
        Badge updateBadge = repository.findById(id)
                // If we didn't find the badge, we update it
                .map(badge -> {
                    BadgeService.authorizedBadge(badge);
                    badge.setName(newBadge.getName());
                    badge.setDescription(newBadge.getDescription());
                    badge.setIcon(newBadge.getIcon());
                    return repository.save(badge);
                })
                // Else we create it with the specified id
                .orElseGet(() -> {
                    newBadge.setId(id);
                    return repository.save(BadgeService.getBadgeFromDTO(newBadge));
                });
        return ResponseEntity.ok(BadgeService.getBadgeDTOFromBadge(updateBadge));
    }

    @ApiOperation("Delete a badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping(value = "/badges/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).map(badge -> {
            BadgeService.authorizedBadge(badge);
            repository.delete(badge);
            return BadgeService.getBadgeDTOFromBadge(badge);
        }).orElseThrow(() -> new BadgeNotFoundException(id));
        return ResponseEntity.noContent().build();
    }
}

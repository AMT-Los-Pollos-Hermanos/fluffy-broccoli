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
    List<Badge> all() {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findAllByApplication(app);
    }

    @ApiOperation("Get only one badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/badges/{id}", produces = "application/json")
    Badge one(@PathVariable Long id) {
        Badge badge = repository.findById(id).orElseThrow(() -> new BadgeNotFoundException(id));
        return authorizedBadge(badge);
    }

    @ApiOperation("Add a new badge")
    @PostMapping(value = "/badges", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Badge newBadge(@RequestBody Badge badge) {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (app == null) {
            throw new RuntimeException("No auth principal found");
        }
        badge.setApplication(app);
        return repository.save(badge);
    }

    @ApiOperation("Update a badge")
    @PutMapping(value = "/badges/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Badge> update(@RequestBody Badge newBadge, @PathVariable Long id) {
        Badge updateBadge = repository.findById(id)
                // If we didn't find the badge, we update it
                .map(badge -> {
                    authorizedBadge(badge);
                    badge.setName(newBadge.getName());
                    badge.setDescription(newBadge.getDescription());
                    badge.setIcon(newBadge.getIcon());
                    return repository.save(badge);
                })
                // Else we create it with the specified id
                .orElseGet(() -> {
                    newBadge.setId(id);
                    return repository.save(newBadge);
                });
        return ResponseEntity.ok(updateBadge);
    }

    @ApiOperation("Delete a badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping(value = "/badges/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).map(badge -> {
            authorizedBadge(badge);
            repository.delete(badge);
            return badge;
        }).orElseThrow(() -> new BadgeNotFoundException(id));
        return ResponseEntity.noContent().build();
    }

    // check badge if it's own by current app before getting it
    private Badge authorizedBadge(Badge badge){
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(badge.getApplication().getApiKey().compareTo(app.getApiKey()) == 0){
            return badge;
        }else{
            throw new BadgeNotAuthorizedException(badge.getId(), app.getApiKey());
        }
    }
}

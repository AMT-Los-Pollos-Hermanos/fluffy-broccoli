package ch.heigvd.broccoli.pointscale;

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

@Api(tags = "Pointscales")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
class PointScaleController {

    private final PointScaleRepository repository;

    PointScaleController(PointScaleRepository repository) {
        this.repository = repository;
    }

    @ApiOperation("Get all point-scales")
    @GetMapping(value = "/pointscales", produces = "application/json")
    List<PointScale> all() {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findAllByApplication(app);
    }

    @ApiOperation("Get only one point-scale")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/pointscales/{id}", produces = "application/json")
    PointScale one(@PathVariable Long id) {
        PointScale pointScale = repository.findById(id).orElseThrow(() -> new PointScaleNotFoundException(id));
        return authorizedPointScale(pointScale);
    }

    @ApiOperation("Add a new point-scale")
    @PostMapping(value = "/pointscales", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    PointScale newPointScale(@RequestBody PointScale pointScale) {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (app == null) {
            throw new RuntimeException("No auth principal found");
        }
        pointScale.setApplication(app);
        return repository.save(pointScale);
    }

    @ApiOperation("Update a point-scale")
    @PutMapping(value = "/pointscales/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PointScale> update(@RequestBody PointScale newPointScale, @PathVariable Long id) {
        PointScale updatePointScale = repository.findById(id)
                // If we didn't find the point-scale, we update it
                .map(pointScale -> {
                    authorizedPointScale(pointScale);
                    return repository.save(pointScale);
                })
                // Else we create it with the specified id
                .orElseGet(() -> {
                    newPointScale.setId(id);
                    return repository.save(newPointScale);
                });
        return ResponseEntity.ok(updatePointScale);
    }

    @ApiOperation("Delete a point-scale")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping(value = "/pointscales/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).map(pointScale -> {
            authorizedPointScale(pointScale);
            repository.delete(pointScale);
            return pointScale;
        }).orElseThrow(() -> new PointScaleNotFoundException(id));
        return ResponseEntity.noContent().build();
    }

    // check point-scale if it's own by current app before getting it
    private PointScale authorizedPointScale(PointScale pointScale) {
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (pointScale.getApplication().getApiKey().compareTo(app.getApiKey()) == 0) {
            return pointScale;
        } else {
            throw new PointScaleNotAuthorizedException(pointScale.getId(), app.getApiKey());
        }
    }
}

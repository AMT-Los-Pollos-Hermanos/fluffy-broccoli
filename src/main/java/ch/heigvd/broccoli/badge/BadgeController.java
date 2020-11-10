package ch.heigvd.broccoli.badge;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Badges", description = "Gestion des badges")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
class BadgeController {

    private final BadgeRepository repository;
    private final BadgeModelAssembler assembler;

    BadgeController(BadgeRepository repository, BadgeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @ApiOperation("Tous les badges")
    @GetMapping(value = "/badges", produces = "application/hal+json")
    CollectionModel<EntityModel<Badge>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @ApiOperation("Un seul badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/badges/{id}", produces = "application/hal+json")
    EntityModel<Badge> one(@PathVariable Long id) {
        Badge badge = repository.findById(id).orElseThrow(() -> new BadgeNotFoundException(id));
        return assembler.toModel(badge);
    }

    @ApiOperation("Nouveau badge")
    @PostMapping(value = "/badges", consumes = "application/json", produces = "application/hal+json")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EntityModel<Badge>> newBadge(@RequestBody Badge badge) {
        EntityModel<Badge> entityModel = assembler.toModel(repository.save(badge));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @ApiOperation("Mise à jour d'un badge")
    @PutMapping(value = "/badges/{id}", consumes = "application/json", produces = "application/hal+json")
    ResponseEntity<EntityModel<Badge>> update(@RequestBody Badge newBadge, @PathVariable Long id) {
        Badge updateBadge = repository.findById(id)
                // Si on trouve un badge avec l'ID, on le met à jour
                .map(badge -> {
                    badge.setName(newBadge.getName());
                    badge.setDescription(newBadge.getDescription());
                    badge.setIcon(newBadge.getIcon());
                    return repository.save(badge);
                })
                // Sinon, on le crée avec l'ID spécifié
                .orElseGet(() -> {
                    newBadge.setId(id);
                    return repository.save(newBadge);
                });
        EntityModel<Badge> entityModel = assembler.toModel(updateBadge);
        return ResponseEntity.ok(entityModel);
    }

    @ApiOperation("Suppression d'un badge")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping(value = "/badges/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.findById(id).map(badge -> {
            repository.delete(badge);
            return badge;
        }).orElseThrow(() -> new BadgeNotFoundException(id));
        return ResponseEntity.noContent().build();
    }


}

package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.pointscale.PointScaleDTO;
import ch.heigvd.broccoli.application.pointscale.PointScaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final PointScaleService service;

    PointScaleController(PointScaleService service) {
        this.service = service;
    }

    @ApiOperation("Get all point-scales")
    @GetMapping(value = "/pointscales", produces = "application/json")
    List<PointScaleDTO> all() {
        return service.all();
    }

    @ApiOperation("Get only one point-scale")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @GetMapping(value = "/pointscales/{id}", produces = "application/json")
    PointScaleDTO one(@PathVariable Long id) {
        return service.one(id);
    }

    @ApiOperation("Add a new point-scale")
    @PostMapping(value = "/pointscales", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    PointScaleDTO newPointScale(@RequestBody PointScaleDTO pointScale) {
       return service.add(pointScale);
    }

    @ApiOperation("Update a point-scale")
    @PutMapping(value = "/pointscales/{id}", consumes = "application/json", produces = "application/json")
    PointScaleDTO update(@RequestBody PointScaleDTO newPointScale, @PathVariable Long id) {
        return service.update(id, newPointScale);
    }

    @ApiOperation("Delete a point-scale")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
    })
    @DeleteMapping(value = "/pointscales/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

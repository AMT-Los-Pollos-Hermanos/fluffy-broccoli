package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.user.UserDTO;
import ch.heigvd.broccoli.application.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Api(tags = "Users")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})
@RestController
class UserController {

    private final UserService service;

    public UserController(UserService service){ this.service = service; }

    @ApiOperation("Get all users")
    @GetMapping(value = "users", produces = "application/json")
    List<UserDTO> all() { return service.all(); }

    @ApiOperation("Get only one user")
    @GetMapping(value = "users/{id}", produces = "application/json")
    UserDTO one(@PathVariable UUID id) { return service.one(id); }
}

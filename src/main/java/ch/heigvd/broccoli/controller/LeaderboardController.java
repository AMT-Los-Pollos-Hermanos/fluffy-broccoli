package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.leaderboard.LeaderboardService;
import ch.heigvd.broccoli.application.user.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "Leaderboards")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
})

@RestController
public class LeaderboardController {

    private final LeaderboardService service;

    LeaderboardController(LeaderboardService service) { this.service = service; }

    @ApiOperation("Get leaderboard")
    @GetMapping(value = "leaderboard", produces = "application/json")
    Map<UserDTO, Double> get(int nbUsers) { return service.get(nbUsers); }
}

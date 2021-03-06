package ch.heigvd.broccoli.controller;

import ch.heigvd.broccoli.application.leaderboard.LeaderboardDTO;
import ch.heigvd.broccoli.application.leaderboard.LeaderboardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    LeaderboardDTO get(@RequestParam int nbUsers, @RequestParam int page) {
        return service.get(nbUsers, page);
    }
}

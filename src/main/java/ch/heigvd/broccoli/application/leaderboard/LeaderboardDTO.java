package ch.heigvd.broccoli.application.leaderboard;

import ch.heigvd.broccoli.application.user.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class LeaderboardDTO {
    Map<UserDTO, Double> leaderboard;
}

package ch.heigvd.broccoli.application.leaderboard;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class LeaderboardDTO {
    Map<UUID, Double> leaderboard;
}

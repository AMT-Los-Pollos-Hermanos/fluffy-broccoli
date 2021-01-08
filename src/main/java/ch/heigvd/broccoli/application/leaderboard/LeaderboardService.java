package ch.heigvd.broccoli.application.leaderboard;

import ch.heigvd.broccoli.application.user.UserDTO;
import ch.heigvd.broccoli.domain.user.UserEntity;
import ch.heigvd.broccoli.domain.user.UserRepository;
import ch.heigvd.broccoli.domain.userreceivepoint.UserReceivePoint;
import ch.heigvd.broccoli.domain.userreceivepoint.UserReceivePointRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LeaderboardService {

    private final UserReceivePointRepository userReceivePointRepository;
    private final UserRepository userRepository;

    public LeaderboardService(UserReceivePointRepository userReceivePointRepository, UserRepository userRepository) {
        this.userReceivePointRepository = userReceivePointRepository;
        this.userRepository = userRepository;
    }

    public LeaderboardDTO get(int nbUsers, int page) {
        Pageable sortedByPoints = PageRequest.of(page, nbUsers);
        Page<UserEntity> userEntities = userRepository.findAll(sortedByPoints);
        Map<Integer, UserDTO> rankingUsers = new TreeMap<>(Collections.reverseOrder());
        Map<UUID, Integer> nRankingUsers = new LinkedHashMap<>();

        // count points for each users
        for (UserEntity userEntity : userEntities) {
            Integer point = getPointsUser(userEntity);
            rankingUsers.put(point, toDTO(userEntity));
        }

        // select n first users for the leaderboard
        int count = 0;
        for (Map.Entry<Integer, UserDTO> entry : rankingUsers.entrySet()) {
            if (count >= nbUsers) break;

            nRankingUsers.put(entry.getValue().getId(), entry.getKey());
            count++;
        }

        return LeaderboardDTO.builder().leaderboard(nRankingUsers).build();
    }

    public Integer getPointsUser(UserEntity userEntity) {
        List<UserReceivePoint> userPoints = userReceivePointRepository.findAllByUserEntity(userEntity);
        Integer points = 0;

        // count the points
        for (UserReceivePoint userPoint : userPoints) {
            points += userPoint.getPoints();
        }

        return points;
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .build();
    }

}

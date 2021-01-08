package ch.heigvd.broccoli.application.leaderboard;

import ch.heigvd.broccoli.application.user.UserDTO;
import ch.heigvd.broccoli.domain.user.PagingSortingUserRepository;
import ch.heigvd.broccoli.domain.user.UserEntity;
import ch.heigvd.broccoli.domain.user.UserRepository;
import ch.heigvd.broccoli.domain.userreceivepoint.UserReceivePoint;
import ch.heigvd.broccoli.domain.userreceivepoint.UserReceivePointRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LeaderboardService {

    private final UserReceivePointRepository userReceivePointRepository;
    private final UserRepository userRepository;
    private final PagingSortingUserRepository pagingSortingUserRepository;

    public LeaderboardService(UserReceivePointRepository userReceivePointRepository, UserRepository userRepository, PagingSortingUserRepository pagingSortingUserRepository) {
        this.userReceivePointRepository = userReceivePointRepository;
        this.userRepository = userRepository;
        this.pagingSortingUserRepository = pagingSortingUserRepository;
    }

    public LeaderboardDTO get(int nbUsers, int page) {
//        Pageable sortedByPoints = PageRequest.of(page, nbUsersm )
        List<UserEntity> userEntities = userRepository.findAll();
        Map<Double, UserDTO> rankingUsers = new TreeMap<>(Collections.reverseOrder());
        Map<UUID, Double> nRankingUsers = new LinkedHashMap<>();

        // count points for each users
        for (UserEntity userEntity : userEntities) {
            double point = getPointsUser(userEntity);
            rankingUsers.put(point, toDTO(userEntity));
        }

        // select n first users for the leaderboard
        int count = 0;
        for (Map.Entry<Double, UserDTO> entry : rankingUsers.entrySet()) {
            if (count >= nbUsers) break;

            nRankingUsers.put(entry.getValue().getId(), entry.getKey());
            count++;
        }

        return LeaderboardDTO.builder().leaderboard(nRankingUsers).build();
    }

    public double getPointsUser(UserEntity userEntity) {
        List<UserReceivePoint> userPoints = userReceivePointRepository.findAllByUserEntity(userEntity);
        double points = 0;

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

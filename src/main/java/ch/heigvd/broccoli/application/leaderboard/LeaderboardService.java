package ch.heigvd.broccoli.application.leaderboard;

import ch.heigvd.broccoli.application.user.UserDTO;
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

    public LeaderboardService(UserReceivePointRepository userReceivePointRepository, UserRepository userRepository) {
        this.userReceivePointRepository = userReceivePointRepository;
        this.userRepository = userRepository;
    }

    public LeaderboardDTO get(int nbUsers, int page) {
        List<UserEntity> userEntities = userRepository.findAll();
        SortedMap<Integer, UserDTO> rankingUsers = new TreeMap<>(Collections.reverseOrder());
        ArrayList<UserDTO> sortedUsers = new ArrayList<>();

        // count points for each users
        for (UserEntity userEntity : userEntities) {
            Integer point = getPointsUser(userEntity);
            rankingUsers.put(point, toDTO(userEntity));
        }

        // select n first users for the leaderboard
        int count = 0;
        int added = 0;
        for (Map.Entry<Integer, UserDTO> entry : rankingUsers.entrySet()) {
            if (added >= nbUsers) break;

            if(count >= nbUsers * page) {
                sortedUsers.add(entry.getValue());
                added++;
            }

            count++;
        }

        return LeaderboardDTO.builder().leaderboard(sortedUsers).build();
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
                .points(getPointsUser(userEntity))
                .build();
    }

}

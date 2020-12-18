package ch.heigvd.broccoli.application.leaderboard;

import ch.heigvd.broccoli.application.user.UserDTO;
import ch.heigvd.broccoli.application.user.UserService;
import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.pointscale.PointScale;
import ch.heigvd.broccoli.domain.user.User;
import ch.heigvd.broccoli.domain.user.UserRepository;
import ch.heigvd.broccoli.domain.userpointscale.UserReceivePoint;
import ch.heigvd.broccoli.domain.userpointscale.UserReceivePointRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public LeaderboardDTO get(int nbUsers){
        List<User> users = userRepository.findAll();
        Map<UserDTO, Double> rankingUsers = new TreeMap<>(Collections.reverseOrder());
        Map<UserDTO, Double> nRankingUsers = new TreeMap<>(Collections.reverseOrder());

        // count points for each users
        for(User user : users){
            double point = getPointsUser(user);
            rankingUsers.put(toDTO(user), point);
        }

        // select n first users for the leaderboard
        int count = 0;
        for(Map.Entry<UserDTO, Double> entry:rankingUsers.entrySet()){
            if(count >= nbUsers) break;

            nRankingUsers.put(entry.getKey(), entry.getValue());
            count++;
        }


        return LeaderboardDTO.builder().leaderboard(nRankingUsers).build();
    }

    public double getPointsUser(User user){
        List<UserReceivePoint> userPoints = userReceivePointRepository.findAllByUser(user);
        var userPointsCurrentApp = new ArrayList<UserReceivePoint>();
        double points = 0;

        // get all the points received from current app
        for(UserReceivePoint userPoint : userPoints){
            if(userPoint.getPointScale().getApplication().equals(app())){
                userPointsCurrentApp.add(userPoint);
            }
        }

        // count the points
        for(UserReceivePoint userPoint : userPointsCurrentApp){
            points += userPoint.getPoints();
        }

        return points;
    }


    private Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserDTO toDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .build();
    }

}

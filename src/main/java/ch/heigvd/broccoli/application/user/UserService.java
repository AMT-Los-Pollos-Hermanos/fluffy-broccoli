package ch.heigvd.broccoli.application.user;

import ch.heigvd.broccoli.application.badge.BadgeService;
import ch.heigvd.broccoli.application.leaderboard.LeaderboardService;
import ch.heigvd.broccoli.application.pointscale.PointScaleService;
import ch.heigvd.broccoli.application.userreceivepoint.UserReceivePointDTO;
import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.user.UserEntity;
import ch.heigvd.broccoli.domain.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService{

    private final UserRepository repository;
    private final LeaderboardService leaderboardService;
    private final BadgeService badgeService;
    private final PointScaleService pointScaleService;

    public UserService(UserRepository repository, LeaderboardService leaderboardService, BadgeService badgeService, PointScaleService pointScaleService) {
        this.repository = repository;
        this.leaderboardService = leaderboardService;
        this.badgeService = badgeService;
        this.pointScaleService = pointScaleService;
    }

    public List<UserDTO> all() { return toDTO(repository.findAllByApplication(app())); }

    public UserDTO one(UUID id) { return toDTO(repository.findByIdAndApplication(id, app())); }

    private UserDTO toDTO(Optional<UserEntity> user){
        return user.map(this::toDTO).orElse(null);
    }

    public UserDTO toDTO(UserEntity userEntity){
        return UserDTO.builder()
                .id(userEntity.getId())
                .badges(userEntity.getBadges().stream().map(badgeService::toDTO).collect(Collectors.toList()))
                .points(leaderboardService.getPointsUser(userEntity))
                .userReceivePointDTOs(userEntity.getUserReceivePoints().stream().map(
                        userReceivePoint -> UserReceivePointDTO.builder()
                                .pointScale(pointScaleService.toDTO(userReceivePoint.getPointScale()))
                                .points(userReceivePoint.getPoints())
                                .timestamp(userReceivePoint.getTimestamp())
                                .build()).collect(Collectors.toList())
                )
                .build();
    }

    public List<UserDTO> toDTO(List<UserEntity> userEntities){
        return userEntities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

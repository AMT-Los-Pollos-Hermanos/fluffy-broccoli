package ch.heigvd.broccoli.application.award;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.award.AwardBadge;
import ch.heigvd.broccoli.domain.award.AwardPoint;
import ch.heigvd.broccoli.domain.badge.Badge;
import ch.heigvd.broccoli.domain.badge.BadgeRepository;
import ch.heigvd.broccoli.domain.user.UserEntity;
import ch.heigvd.broccoli.domain.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AwardService {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public AwardService(UserRepository userRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    public void apply(AwardBadge award, UUID userId) {
        userRepository.findByIdAndApplication(userId, app()).map(userEntity -> {
            applyBadgeAwardOnUser(award, userEntity);
            return userEntity;
        }).or(() -> {
            UserEntity newUser = UserEntity.builder()
                    .id(userId)
                    .application(app())
                    .build();
            applyBadgeAwardOnUser(award, newUser);
            return Optional.empty();
        });
    }

    private void applyBadgeAwardOnUser(AwardBadge award, UserEntity userEntity) {
        boolean alreadyGotBadge = false;
        // Prevent duplicate badge for a user
        for (Badge badge : userEntity.getBadges()) {
            if(award.getBadgeId() == badge.getId()) {
                alreadyGotBadge = true;
                break;
            }
        }
        if(!alreadyGotBadge) {
            badgeRepository.findByIdAndApplication(award.getBadgeId(), app()).map(badge -> {
                userEntity.getBadges().add(badge);
                userRepository.save(userEntity);
                return badge;
            }).orElseThrow(() -> new RuntimeException("Badge '" + award.getBadgeId() + "' in event not found"));
        }
    }

    public void apply(AwardPoint award, UUID userId) {
        userRepository.findByIdAndApplication(userId, app());
    }

    public Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

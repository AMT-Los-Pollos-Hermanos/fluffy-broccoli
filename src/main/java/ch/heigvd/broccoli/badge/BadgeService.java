package ch.heigvd.broccoli.badge;

import ch.heigvd.broccoli.application.Application;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class BadgeService {
    static Badge getBadgeFromDTO(BadgeDTO badgeDTO){
        return new Badge(badgeDTO.getId(),
                badgeDTO.getName(),
                badgeDTO.getDescription(),
                badgeDTO.getIcon(),
                (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    static BadgeDTO getBadgeDTOFromBadge(Badge badge){
        return new BadgeDTO(badge.getId(),
                badge.getName(),
                badge.getDescription(),
                badge.getIcon());
    }
    static List<BadgeDTO> getListDTOFromBadges(List<Badge> badges){
        List<BadgeDTO> badgesDTO = new ArrayList<>();
        badges.forEach(badge -> badgesDTO.add(getBadgeDTOFromBadge(badge)));
        return badgesDTO;
    }
    // check badge if it's own by current app before getting it
    static Badge authorizedBadge(Badge badge){
        Application app = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(badge.getApplication().getApiKey().compareTo(app.getApiKey()) == 0){
            return badge;
        }else{
            throw new BadgeNotAuthorizedException(badge.getId(), app.getApiKey());
        }
    }
}

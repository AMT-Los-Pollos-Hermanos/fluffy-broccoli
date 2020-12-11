package ch.heigvd.broccoli.application.badge;

import ch.heigvd.broccoli.application.BaseService;
import ch.heigvd.broccoli.domain.badge.Badge;
import ch.heigvd.broccoli.domain.badge.BadgeRepository;
import org.springframework.stereotype.Service;

@Service
public class BadgeService extends BaseService<BadgeDTO, Badge> {

    BadgeService(BadgeRepository repository) {
        this.repository = repository;
    }

    @Override
    public BadgeDTO add(BadgeDTO badgeDTO) {
        return toDTO(repository.save(Badge.builder()
                .name(badgeDTO.getName())
                .description(badgeDTO.getDescription())
                .icon(badgeDTO.getIcon())
                .application(app())
                .build()));
    }

    @Override
    public BadgeDTO update(Long id, BadgeDTO badgeDTO) {
        return toDTO(repository.findByIdAndApplication(id, app()).map(badge -> {
            badge.setName(badgeDTO.getName());
            badge.setDescription(badgeDTO.getDescription());
            badge.setIcon(badgeDTO.getIcon());
            return repository.save(badge);
        }).orElseThrow(() -> new BadgeNotFoundException(id)));
    }

    @Override
    public BadgeDTO toDTO(Badge badge) {
        return BadgeDTO.builder()
                .id(badge.getId())
                .name(badge.getName())
                .description(badge.getDescription())
                .icon(badge.getIcon())
                .build();
    }

}

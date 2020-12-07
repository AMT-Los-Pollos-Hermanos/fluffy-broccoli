package ch.heigvd.broccoli.application.badge;

import ch.heigvd.broccoli.ServiceInterface;
import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.badge.Badge;
import ch.heigvd.broccoli.domain.badge.BadgeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BadgeService implements ServiceInterface<BadgeDTO, Badge> {

    private final BadgeRepository repository;

    BadgeService(BadgeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BadgeDTO> all() {
        return toDTO(repository.findAllByApplication(app()));
    }

    @Override
    public BadgeDTO one(Long id) {
        return toDTO(repository.findByIdAndApplication(id, app()));
    }

    private BadgeDTO toDTO(Optional<Badge> badge) {
        return badge.map(this::toDTO).orElse(null);
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
    public BadgeDTO delete(Long id) {
        return toDTO(repository.findByIdAndApplication(id, app()).map(badge -> {
            repository.delete(badge);
            return badge;
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

    public List<BadgeDTO> toDTO(List<Badge> badges) {
        return badges.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

package ch.heigvd.broccoli.application.user;

import ch.heigvd.broccoli.application.badge.BadgeService;
import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.user.User;
import ch.heigvd.broccoli.domain.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService{

    private final UserRepository repository;

    public UserService(UserRepository repository) { this.repository = repository; }

    public List<UserDTO> all() { return toDTO(repository.findAllByApplication(app())); }

    public UserDTO one(Long id) { return toDTO(repository.findByIdAndApplication(id, app())); }

    private UserDTO toDTO(Optional<User> user){
        return user.map(this::toDTO).orElse(null);
    }

    public UserDTO toDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .build();
    }

    public List<UserDTO> toDTO(List<User> users){
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Application app() {
        return (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

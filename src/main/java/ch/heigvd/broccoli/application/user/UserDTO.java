package ch.heigvd.broccoli.application.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private long id; // TODO must be UUID or String

    private String firstname;

    private String lastname;

    private String username;

    private Double points;
    // TODO need List<BadgeDTO> and List<UserPointScaleDTO>
}

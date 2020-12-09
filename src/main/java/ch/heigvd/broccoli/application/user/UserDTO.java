package ch.heigvd.broccoli.application.user;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
public class UserDTO {
    @Id
    @GeneratedValue
    private long id;

    private String firstname;

    private String lastname;

    private String username;
}

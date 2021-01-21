package ch.heigvd.broccoli.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private String type;
    private Map<String, String> properties;

    @JsonProperty("user_id")
    private UUID userId;

}

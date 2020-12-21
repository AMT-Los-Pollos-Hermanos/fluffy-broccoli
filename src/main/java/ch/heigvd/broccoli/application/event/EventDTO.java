package ch.heigvd.broccoli.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class EventDTO {

    private String type;
    private Map<String, String> properties;

    @JsonProperty("user_id")
    private UUID userId;

}

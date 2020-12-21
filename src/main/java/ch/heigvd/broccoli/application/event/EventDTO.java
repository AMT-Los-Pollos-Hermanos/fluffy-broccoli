package ch.heigvd.broccoli.application.event;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class EventDTO {

    private String type;
    private Map<String, String> properties;
    private UUID userId;

}

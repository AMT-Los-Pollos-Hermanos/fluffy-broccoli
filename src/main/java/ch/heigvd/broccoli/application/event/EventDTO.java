package ch.heigvd.broccoli.application.event;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EventDTO {

    private String type;
    private Map<String, String> properties;
    private long userId;

}

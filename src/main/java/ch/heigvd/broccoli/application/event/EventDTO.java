package ch.heigvd.broccoli.application.event;

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
    private UUID userId;

}

package ch.heigvd.broccoli.application.pointscale;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointScaleDTO {

    private long id;

    private String name;

}

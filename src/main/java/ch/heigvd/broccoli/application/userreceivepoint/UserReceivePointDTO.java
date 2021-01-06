package ch.heigvd.broccoli.application.userreceivepoint;

import ch.heigvd.broccoli.application.pointscale.PointScaleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserReceivePointDTO {

    private PointScaleDTO pointScale;

    private Integer points;

    private Date timestamp;

}
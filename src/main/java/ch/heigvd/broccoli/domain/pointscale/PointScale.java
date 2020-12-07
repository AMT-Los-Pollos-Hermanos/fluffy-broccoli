package ch.heigvd.broccoli.domain.pointscale;

import ch.heigvd.broccoli.domain.application.Application;
import ch.heigvd.broccoli.domain.userpointscale.UserPointScale;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointScale {

    @Id @GeneratedValue
    @ApiModelProperty(example = "1", position = 1)
    private long id;

    @ManyToOne
    private Application application;

    @OneToMany
    private List<UserPointScale> userPointScales;
}

package ch.heigvd.broccoli.domain.pointscale;

import ch.heigvd.broccoli.domain.application.Application;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

}

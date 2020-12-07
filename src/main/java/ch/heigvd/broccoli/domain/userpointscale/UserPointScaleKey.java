package ch.heigvd.broccoli.domain.userpointscale;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserPointScaleKey implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "pointscale_id")
    private long pointscaleId;
}

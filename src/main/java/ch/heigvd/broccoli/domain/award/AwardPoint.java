package ch.heigvd.broccoli.domain.award;

import lombok.Data;

@Data
public class AwardPoint implements Award {

    private Long pointScale;
    private Integer amount;

}

package ch.heigvd.broccoli.domain.award;

import lombok.Data;

@Data
public class AwardPoint implements Award {

    private String pointScale;
    private int amount;

}

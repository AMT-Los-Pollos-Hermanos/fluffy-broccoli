package ch.heigvd.broccoli.rule.specification;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RuleIf implements Serializable {

    private String type;

}

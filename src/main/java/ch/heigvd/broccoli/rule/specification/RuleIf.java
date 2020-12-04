package ch.heigvd.broccoli.rule.specification;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class RuleIf implements Serializable {

    @NotBlank
    private String type;

    private Map<String, String> properties;

}

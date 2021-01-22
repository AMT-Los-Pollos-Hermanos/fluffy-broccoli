package ch.heigvd.broccoli.domain.rule.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleIf {

    private String type;

    private Map<String, String> properties;

}

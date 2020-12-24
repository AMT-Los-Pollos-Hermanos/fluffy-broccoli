package ch.heigvd.broccoli.domain.rule.specification;

import lombok.Data;

import java.util.Map;

@Data
public class RuleIf {

    private String type;

    private Map<String, String> properties;

}

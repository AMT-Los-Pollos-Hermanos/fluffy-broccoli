package ch.heigvd.broccoli.rule.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;

public class RuleIfConverter implements AttributeConverter<RuleIf, String> {

    ObjectMapper objectMapper;

    public RuleIfConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(RuleIf ruleIf) {
        try {
            return objectMapper.writeValueAsString(ruleIf);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RuleIf convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, RuleIf.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

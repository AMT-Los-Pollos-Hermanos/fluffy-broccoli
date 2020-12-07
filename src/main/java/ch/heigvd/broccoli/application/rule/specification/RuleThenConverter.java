package ch.heigvd.broccoli.application.rule.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;

public class RuleThenConverter implements AttributeConverter<RuleThen, String> {

    ObjectMapper objectMapper;

    public RuleThenConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(RuleThen ruleThen) {
        try {
            return objectMapper.writeValueAsString(ruleThen);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RuleThen convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, RuleThen.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
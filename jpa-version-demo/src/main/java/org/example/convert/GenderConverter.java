package org.example.convert;

import org.example.entity.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {
 
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.getValue();
    }
 
    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.fromString(dbData);
    }
}
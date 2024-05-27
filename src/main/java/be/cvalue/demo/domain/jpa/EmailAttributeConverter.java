package be.cvalue.demo.domain.jpa;

import be.cvalue.demo.domain.Email1;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailAttributeConverter implements AttributeConverter<Email1, String> {

    @Override
    public String convertToDatabaseColumn(Email1 attribute) {
        return attribute.value();
    }

    @Override
    public Email1 convertToEntityAttribute(String dbData) {
        return new Email1(dbData);
    }
}

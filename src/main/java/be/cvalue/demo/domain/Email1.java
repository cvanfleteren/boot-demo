package be.cvalue.demo.domain;


import be.cvalue.demo.domain.jpa.EmailAttributeConverter;
import jakarta.persistence.Convert;

//@Convert(converter = EmailAttributeConverter.class)
public record Email1(String value) {
}

package be.cvalue.demo.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Name(String first, String last) {
}

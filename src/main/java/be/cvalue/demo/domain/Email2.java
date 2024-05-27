package be.cvalue.demo.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Email2(String email) {
}

package be.cvalue.demo.domain;

import java.util.List;

public record ComplexSettings(String foo, String bar, List<Boolean> booleans) {
}

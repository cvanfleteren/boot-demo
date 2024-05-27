package be.cvalue.demo.domain.jpa;

import be.cvalue.demo.domain.CustomerId;

public class CustomerIdType extends StringWrapperType<CustomerId> { // <1>

    public CustomerIdType() {
        super(CustomerId.class, CustomerId::value, CustomerId::new);
    }
}

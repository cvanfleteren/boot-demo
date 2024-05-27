package be.cvalue.demo.domain.jpa;

import be.cvalue.demo.domain.CustomerSettingsId;

public class CustomerSettingsIdType extends UUIDWrapperType<CustomerSettingsId> { // <1>

    public CustomerSettingsIdType() {
        super(CustomerSettingsId.class, CustomerSettingsId::value, CustomerSettingsId::new);
    }

}

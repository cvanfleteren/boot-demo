package be.cvalue.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSettingsRepository extends JpaRepository<CustomerSettings, CustomerSettingsId>{

    Optional<CustomerSettings> findByCustomer(Customer customer);
    Optional<CustomerSettings> findByCustomerId(CustomerId customerId);
}

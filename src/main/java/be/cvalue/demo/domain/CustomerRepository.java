package be.cvalue.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {

    @Query("""
           select c from Customer c where c.name.last = :lastName
            """)
    Optional<Customer> findByLastName(String lastName);

    Optional<Customer> findByEmail1(Email1 email1);

    @Query("""
            select c from Customer c where c.email1 like :email1
            """)
    Optional<Customer> findByEmail1(String email1);

    @Query("""
            select c from Customer c where c.email2.email like :email2
            """)
    Optional<Customer> findByEmail2(String email2);

    Optional<Customer> findByEmail2(Email2 email2);

}

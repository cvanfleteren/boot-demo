package be.cvalue.demo.domain;

import be.cvalue.demo.domain.jpa.CustomerIdType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
public class Customer {

    @JavaType(CustomerIdType.class)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Id
    private CustomerId id;

    @NotNull
    @Embedded
    private Name name;

    @NotNull
    private Email1 email1;

    @Embedded
    private Email2 email2;

}

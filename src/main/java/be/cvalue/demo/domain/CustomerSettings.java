package be.cvalue.demo.domain;

import be.cvalue.demo.domain.jpa.CustomerSettingsIdType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Setter
@Getter
public class CustomerSettings {

    @Id
    @JavaType(CustomerSettingsIdType.class)
    @JdbcTypeCode(SqlTypes.UUID)
    CustomerSettingsId id;

    @ManyToOne
    Customer customer;

    @JdbcTypeCode(SqlTypes.JSON)
    ComplexSettings settings;

}

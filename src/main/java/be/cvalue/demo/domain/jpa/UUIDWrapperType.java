package be.cvalue.demo.domain.jpa;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.java.UUIDJavaType;

import java.util.UUID;
import java.util.function.Function;

public abstract class UUIDWrapperType<T> extends AbstractClassJavaType<T> { // <1>

    private final Function<T, UUID> toStringer;
    private final Function<UUID, T> factory;

    protected UUIDWrapperType(Class<T> clazz, Function<T, UUID> toStringer, Function<UUID, T> factory) {
        super(clazz);
        this.toStringer = toStringer;
        this.factory = factory;
    }

    public String toString(Class value) {
        return value.getName();
    }

    @Override
    public <X> X unwrap(T value, Class<X> type, WrapperOptions options) {
        return  UUIDJavaType.INSTANCE.unwrap(toStringer.apply(value), type, options);
    }

    @Override
    public <X> T wrap(X value, WrapperOptions options) {
        if(value.getClass().isAssignableFrom(getJavaType())) {
            return (T)value;
        }

        return factory.apply(UUIDJavaType.INSTANCE.wrap(value, options));
    }
}

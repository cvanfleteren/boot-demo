package be.cvalue.demo.domain.jpa;

import lombok.SneakyThrows;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.java.StringJavaType;

import java.util.Objects;
import java.util.function.Function;

public abstract class StringWrapperType<T> extends AbstractClassJavaType<T> { // <1>

    private final Function<T, String> toStringer;
    private final Function<String, T> factory;

    protected StringWrapperType(Class<T> clazz, Function<T, String> toStringer, Function<String, T> factory) {
        super(clazz);
        this.toStringer = Objects.requireNonNull(toStringer);
        this.factory = Objects.requireNonNull(factory);
    }

    public String toString(Class value) {
        return value.getName();
    }

    @SneakyThrows
    public T fromString(CharSequence string) {
        if (string == null) {
            return null;
        }

        return factory.apply(string.toString());
    }

    @Override
    public <X> X unwrap(T value, Class<X> type, WrapperOptions options) {
        return  StringJavaType.INSTANCE.unwrap(toStringer.apply(value), type, options);
    }

    @Override
    public <X> T wrap(X value, WrapperOptions options) {
        if(value.getClass().isAssignableFrom(getJavaType())) {
            return (T)value;
        }

        return factory.apply(StringJavaType.INSTANCE.wrap(value, options));
    }
}

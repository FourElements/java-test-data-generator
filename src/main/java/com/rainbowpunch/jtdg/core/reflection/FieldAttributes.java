package com.rainbowpunch.jtdg.core.reflection;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

/**
 * A friendly wrapper for Field objects.
 */
public class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field) {
        this.field = field;
    }

    public String getName() {
        return field.getName();
    }

    public Field getField() {
        return field;
    }

    public ClassAttributes getType() {
        return ClassAttributes.create(field.getType(), field.getGenericType());
    }

    /**
     * Constructs a setter function that is capable of setting a field on a provided instance.
     * @param <T> the instance type of the object.
     * @param <U> the value type of the field in the object.
     * @return a binary consumer function that mutates a field on an object.
     */
    public <T, U> BiConsumer<T, U> getSetter() {
        final Field f = field; // limit closure
        return (instance, value) -> {
            f.setAccessible(true);
            try {
                f.set(instance, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }
}

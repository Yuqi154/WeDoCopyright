package org.hiedacamellia.wedocopyright.client.util;

import java.util.function.Supplier;

public class ValueHolder<T> implements Supplier<T> {

    private T value;

    public ValueHolder(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return null;
    }
}

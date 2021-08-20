package com.winson.jdkapi.java8.function;

/**
 * @author winson
 * @date 2021/6/18
 **/
public interface MyFunction<T, R> {

    R apply(T t);

    default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        return (V v) -> apply(before.apply(v));
    }

    default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
        return (T t) -> after.apply(apply(t));
    }

    static <T> MyFunction<T, T> identity() {
        return (T t) -> t;
    }

}

package com.winson.jdkapi.java8.function;

/**
 * @author winson
 * @date 2021/6/18
 **/
public interface MyBiFunction<T, U, R> {

    R apply(T t, U u);

    default <V> MyBiFunction<T, U, V> andThen(MyFunction<R, V> after) {
        return (T t, U u) -> after.apply(apply(t, u));
    }

}

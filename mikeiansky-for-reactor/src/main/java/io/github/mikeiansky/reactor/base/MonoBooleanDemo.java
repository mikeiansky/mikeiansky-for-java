package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 *
 * @author mike ian
 * @date 2025/6/16
 * @desc
 **/
public class MonoBooleanDemo {

    public static void main(String[] args) {
        System.out.println(MonoBooleanDemo.class.getName());
        Mono.just("hello")
                .map(Objects::isNull)
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new RuntimeException("test error")))
                .subscribe(System.out::println);

    }

}

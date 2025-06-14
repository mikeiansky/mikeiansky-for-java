package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

/**
 * @author mike ian
 * @date 2025/6/14
 * @desc
 **/
public class MonoBlockDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .doOnNext(System.out::println)
                .block();

    }

}

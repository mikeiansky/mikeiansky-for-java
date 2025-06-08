package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

/**
 * @author mike ian
 * @date 2025/6/8
 * @desc
 **/
public class ReactorMonoDemo {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("Hello, Reactor Mono!");
        mono.subscribe(System.out::println);

    }

}

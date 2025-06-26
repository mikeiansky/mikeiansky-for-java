package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

/**
 *
 * @author mike ian
 * @date 2025/6/26
 * @desc
 **/
public class MonoOpDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .then(Mono.just("world"))
                .subscribe(System.out::println);

        System.out.println("\n==========");

        Mono.empty()
                .switchIfEmpty(Mono.just("empty"))
                .subscribe(System.out::println);

    }

}

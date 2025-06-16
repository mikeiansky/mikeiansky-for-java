package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

/**
 *
 * @author mike ian
 * @date 2025/6/16
 * @desc
 **/
public class MonoEmptyDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .zipWith(Mono.empty())
                .map(t -> t.getT1() + " :: " + t.getT2())
                .doOnNext(t -> System.out.println(t))
                .subscribe();

    }

}

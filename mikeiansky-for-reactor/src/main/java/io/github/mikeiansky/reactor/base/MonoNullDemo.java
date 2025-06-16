package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

/**
 *
 * @author mike ian
 * @date 2025/6/16
 * @desc
 **/
public class MonoNullDemo {

    public static void main(String[] args) {
        String key = "";
        Mono.just(key.length() > 0)
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.just(true))
                .doOnNext(v -> {
                    System.out.println("test null : " + v);
                }).subscribe();

    }

}

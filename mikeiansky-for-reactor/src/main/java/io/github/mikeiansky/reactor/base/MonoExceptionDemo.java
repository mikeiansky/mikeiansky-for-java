package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 *
 * @author mike ian
 * @date 2025/6/16
 * @desc
 **/
public class MonoExceptionDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("ac");
                        Mono.error(new RuntimeException("ccc"));
                    }
                })
                .subscribe(System.out::println);

    }

}

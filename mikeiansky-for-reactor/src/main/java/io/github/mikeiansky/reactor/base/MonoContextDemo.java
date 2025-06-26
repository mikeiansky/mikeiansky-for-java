package io.github.mikeiansky.reactor.base;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author mike ian
 * @date 2025/6/14
 * @desc
 **/
public class MonoContextDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .flatMap(prev-> {
                    return Mono.deferContextual(contextView -> {
                        System.out.println(contextView);

                        return Mono.just(prev).contextCapture();
                    });
                }).map(key -> {
                    return key;
                })
                .contextWrite(Context.of("k1", "v1"))
                .contextWrite(Context.of("k2", "v2"))
                .subscribe(System.out::println)
        ;

    }

}

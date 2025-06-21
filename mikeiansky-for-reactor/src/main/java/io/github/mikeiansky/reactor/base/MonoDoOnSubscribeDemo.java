package io.github.mikeiansky.reactor.base;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * @author mike ian
 * @date 2025/6/21
 * @desc
 **/
public class MonoDoOnSubscribeDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .doOnSubscribe(subscription -> {
                    System.out.println(subscription);
                    subscription.request(1);
                })
                .subscribe(System.out::println);

    }

}

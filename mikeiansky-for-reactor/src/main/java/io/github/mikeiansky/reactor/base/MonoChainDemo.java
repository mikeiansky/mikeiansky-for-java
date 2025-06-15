package io.github.mikeiansky.reactor.base;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * @author mike ian
 * @date 2025/6/14
 * @desc
 **/
public class MonoChainDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                .doOnNext(System.out::println)
//                .doOnNext(System.out::println)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) {
                        System.out.println("accept ....... ");
                        subscription.request(1);
                    }
                })
//                .doOnNext(System.out::println)
                .subscribe(System.out::println);

    }

}

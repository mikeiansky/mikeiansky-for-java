package com.winson.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author winson
 * @date 2022/6/21
 **/
public class ReactorCoreDemoV1 {

    public static void main(String[] args) {

        Flux.just("Hello")
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(String s) {
                        return new Flux<String>() {
                            @Override
                            public void subscribe(CoreSubscriber<? super String> actual) {
                                System.out.println("subscribe delegate ... ");
                                actual.onNext(s);
                            }
                        };
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("reactor core flux subscribe accept s : " + s);
                    }
                });

    }

}

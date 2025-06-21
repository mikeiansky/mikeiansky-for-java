package io.github.mikeiansky.reactor.base;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

/**
 * @author mike ian
 * @date 2025/6/21
 * @desc
 **/
public class MonoBaseDemo {

    public static void main(String[] args) {

        Mono.just("hello")
                        .subscribe(System.out::println);

        Mono.just("hello")
                .subscribe(new CoreSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("on subscribe .... : " + s);
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("subscribe onext :: " + s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe complete ... ");

                    }
                });

    }

}

package io.github.mikeiansky.rxjava.base;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class MonoDemo {

    public static void main(String[] args) throws InterruptedException {


        Mono<String> mono = Mono.just("Hello");
        mono.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(Thread.currentThread().getName() + ": " + s);
            }
        });

        Mono.delay(Duration.ofSeconds(1))
//                .then(Mono.delay(Duration.ofSeconds(2)))
                .then(Mono.just("World"))
                .subscribe(System.out::println);

        Thread.sleep(2000);

    }

}

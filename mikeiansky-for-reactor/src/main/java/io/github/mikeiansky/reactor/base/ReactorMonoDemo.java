package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author mike ian
 * @date 2025/6/8
 * @desc
 **/
public class ReactorMonoDemo {

    public static void main(String[] args) throws InterruptedException {

        Mono<String> mono = Mono.just("Hello, Reactor Mono!");
        mono.subscribe(System.out::println);

        System.out.println("before mono delay");
        Mono.delay(Duration.ofSeconds(2))
                .then(Mono.just("hello after delay"))
                .subscribe(System.out::println);

        Thread.sleep(3000);
    }

}

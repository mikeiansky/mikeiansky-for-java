package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.Callable;

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


        Mono.delay(Duration.ZERO).then(Mono.just("task1").map(key->{
            System.out.println(key + " running @ thread : " + Thread.currentThread().getName());
            return key;
        })).subscribe();

        Mono.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("task2 running @ thread : " + Thread.currentThread().getName());
                return "task2";
            }
        }).subscribeOn(Schedulers.boundedElastic()).subscribe(System.out::println);

        Thread.sleep(3000);
    }

}

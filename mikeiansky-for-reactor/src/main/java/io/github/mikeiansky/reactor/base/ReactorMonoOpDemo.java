package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.function.Function;

/**
 *
 * @author mike ian
 * @date 2025/6/10
 * @desc
 **/
public class ReactorMonoOpDemo {

    public static void main(String[] args) throws InterruptedException {
        Mono<String> one = Mono.delay(Duration.ofSeconds(3))
                .then(Mono.just("Hello"))
                .doOnNext(System.out::println);

        Mono<String> two = Mono.delay(Duration.ofSeconds(2))
                .then(Mono.just("world"))
                .doOnNext(System.out::println);

        Mono<String> three = Mono.delay(Duration.ofSeconds(1))
                .then(Mono.just("mike"))
                .doOnNext(System.out::println);

        Mono<String> zip = Mono.zip(one, two, three)
                        .map(new Function<Tuple3<String, String, String>, String>() {
                            @Override
                            public String apply(Tuple3<String, String, String> objects) {
                                return objects.getT1();
                            }
                        });

        zip.block();

//        Mono.delay(Duration.ofSeconds(3))
//                .then(Mono.just("cccc"))
//                .doOnNext(System.out::println)
//                .block();


    }

}

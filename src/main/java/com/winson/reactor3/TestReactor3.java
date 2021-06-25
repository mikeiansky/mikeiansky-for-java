package com.winson.reactor3;

//import org.reactivestreams.Publisher;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author winson
 * @date 2021/6/2
 **/
public class TestReactor3 {

//    private static Flux<String> getZipDescFlux() {
//        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
//        return Flux.fromArray(desc.split("\\s+"));  // 1
//    }
//
//    public static void testSimpleOperators() throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);  // 2
//        Flux.zip(
//                getZipDescFlux(),
//                Flux.interval(Duration.ofMillis(200)))  // 3
//                .subscribe(t -> System.out.println(t.getT1()), null, countDownLatch::countDown);    // 4
//        countDownLatch.await(10, TimeUnit.SECONDS);     // 5
//    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test reactor3 start ... ");

//        testSimpleOperators();

//        Flux.just(1, 2, 3, 4)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) {
//                        return integer > 2;
//                    }
//                }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println("accept : " + integer);
//            }
//        });

        System.out.println("test reactor3 stop ... ");
    }

}

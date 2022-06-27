package com.winson.reactor;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @author winson
 * @date 2022/6/24
 **/
public class ReactorMonoDemoV1 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        Mono.just("Hello")
                .delayElement(Duration.ofSeconds(2))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(Thread.currentThread().getName() + " mono subscribe accept : " + s);
                        latch.countDown();
                    }
                });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main complete ... ");

    }

}

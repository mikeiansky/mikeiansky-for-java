package com.winson.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @author winson
 * @date 2022/6/21
 **/
public class ReactorCoreIoDemoV1 {

    public static void main(String[] args) {
        int size = 100;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            Flux.just("Hello-IO")
                    .subscribeOn(Schedulers.elastic())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            System.out.println(Thread.currentThread().getName()
                                    + " , subscribe accept : " + s);
                            latch.countDown();
                        }
                    });
        }


        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main app complete ... ");
    }

}

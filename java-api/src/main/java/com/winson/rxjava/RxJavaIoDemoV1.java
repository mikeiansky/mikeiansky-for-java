package com.winson.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2022/6/22
 **/
public class RxJavaIoDemoV1 {

    public static void main(String[] args) {
        int size = 1;
        AtomicInteger count = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            Flowable.just("Hello-RxJava-Io")
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Throwable {
//                            Thread.sleep(1000);
                            count.incrementAndGet();
                            latch.countDown();
                            System.out.println(Thread.currentThread().getName() + " , accept : " + s);
                        }
                    });
        }


        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main complete ... " + count.get());

    }

}

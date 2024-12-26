package io.github.mikeiansky.rxjava.base;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.subscribers.LambdaSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;


/**
 * @author mike ian
 * @date 2024/12/26
 * @desc
 **/
public class FlowableIODemoV2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flowable.just("hello")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println(Thread.currentThread().getName() + ": " + s);
                        latch.countDown();
                    }

                });


        latch.await();
    }

}

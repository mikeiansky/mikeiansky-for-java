package com.winson.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * @author winson
 * @date 2022/6/18
 **/
public class RxJavaHighApiDemoV1 {

    public static void main(String[] args) {

        Flowable.just("hello", "one", "two", "three")
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println(Thread.currentThread().getName()+" , accept : " + s);
                    }
                });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

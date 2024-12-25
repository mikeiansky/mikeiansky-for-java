package io.github.mikeiansky.rxjava.base;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class ConcurrencyFlowableDemo {

    public static void main(String[] args) {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(v->{
                    System.out.println(Thread.currentThread().getName() + ", " + v);
                });

    }

}

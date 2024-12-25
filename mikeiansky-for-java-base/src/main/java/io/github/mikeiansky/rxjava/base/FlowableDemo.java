package io.github.mikeiansky.rxjava.base;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscribers.LambdaSubscriber;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class FlowableDemo {

    public static void main(String[] args) {

        Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0)
                .subscribe(System.out::println);
                ;
    }

}

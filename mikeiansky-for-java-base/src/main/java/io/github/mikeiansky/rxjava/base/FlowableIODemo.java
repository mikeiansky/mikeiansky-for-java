package io.github.mikeiansky.rxjava.base;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class FlowableIODemo {

    public static void main(String[] args) throws InterruptedException {

        Flowable.just("hello world")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
                .subscribe(v-> {
                    System.out.println(Thread.currentThread().getName()+", Thread current : "+v);
                });

        Thread.sleep(100); // <--- wait for the flow to finish
        System.out.println(Thread.currentThread().getName() + " complete ... ");

    }

}

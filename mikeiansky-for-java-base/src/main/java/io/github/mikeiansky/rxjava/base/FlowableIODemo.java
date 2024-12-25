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

        Flowable.fromCallable(() -> {
                    System.out.println(Thread.currentThread().getName() + " , create ");
                    Thread.sleep(1000); //  imitate expensive computation
                    return "Done";
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
//                .observeOn(Schedulers.newThread())
                .subscribe(v-> {
                    System.out.println(Thread.currentThread().getName()+", Thread current : "+v);
                }, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish

    }

}

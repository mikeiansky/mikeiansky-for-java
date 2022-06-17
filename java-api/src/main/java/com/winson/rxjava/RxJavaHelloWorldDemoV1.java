package com.winson.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import rx.Observable;

/**
 * @author winson
 * @date 2022/6/17
 **/
public class RxJavaHelloWorldDemoV1 {

    public static void main(String[] args) {

        Flowable.just("hello")
                .subscribe(System.out::println);

//        Observable.just(1, 2, 3, 5)
//                .subscribe(System.out::println);

    }

}

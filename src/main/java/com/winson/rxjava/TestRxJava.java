package com.winson.rxjava;

//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.functions.Action;
//import rx.functions.Action1;
//import rx.functions.Func0;
//import rx.functions.Func1;
//import rx.observables.SyncOnSubscribe;
//import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author winson
 * @date 2021/6/2
 **/
public class TestRxJava {

    public static void main(String[] args) {
        System.out.println("test rx java start ... ");

//        Observable.just(1, 2, 3, 4, 5, 6, 7)
//                .flatMap((Func1<Integer, Observable<Integer>>) before -> {
//                    System.out.println("call before : " + before);
//                    return Observable.just(before + 10);
//                })
//                .subscribe(new Subscriber<Integer>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer result) {
//                        System.out.println("on next : " + result);
//                    }
//                });

//        Observable.timer(1, TimeUnit.SECONDS);
//        Observable.just(1, 2, 3, 4, 5)
//                .repeat(Schedulers.immediate())
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("just on next : " + integer);
//                    }
//                });
//
//        System.out.println("test rx java stop ... ");
    }

}

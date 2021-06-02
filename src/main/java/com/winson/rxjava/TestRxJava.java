package com.winson.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.SyncOnSubscribe;

import java.util.List;

/**
 * @author winson
 * @date 2021/6/2
 **/
public class TestRxJava {

    public static void main(String[] args) {
        System.out.println("test rx java start ... ");

        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .flatMap((Func1<Integer, Observable<Integer>>) before -> {
                    System.out.println("call before : " + before);
                    return Observable.just(before + 10);
                })
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer result) {
                        System.out.println("on next : " + result);
                    }
                });

        System.out.println("test rx java stop ... ");
    }

}

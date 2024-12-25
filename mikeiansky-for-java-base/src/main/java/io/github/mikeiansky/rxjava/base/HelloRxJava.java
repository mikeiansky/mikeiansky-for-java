package io.github.mikeiansky.rxjava.base;


import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/**
 * @author mike ian
 * @date 2024/12/23
 * @desc
 **/
public class HelloRxJava {

    public static void main(String[] args) {
//        RxJavaPlugins.setOnFlowableAssembly(new Function<Flowable, Flowable>() {
//            @Override
//            public Flowable apply(Flowable flowable) throws Throwable {
//                System.out.println("update flowable");
//                return flowable;
//            }
//        });
        Flowable.just("Hello Flowable").subscribe(System.out::println);
        Flowable.just("Hello Flowable-01", "Hello Flowable-02").subscribe(System.out::println);

        Observable.just("Hello Observable").subscribe(System.out::println);
        Observable.just("Hello Observable-01", "Hello Observable-02").subscribe(System.out::println);

        Single.just("Hello Single").subscribe(System.out::println);

        Completable.complete().subscribe(System.out::println);

        Maybe.just("Hello Maybe").subscribe(System.out::println);

    }

}

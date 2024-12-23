package io.github.mikeiansky.rxjava.base;


import io.reactivex.rxjava3.core.Flowable;

/**
 * @author mike ian
 * @date 2024/12/23
 * @desc
 **/
public class HelloRxJava {

    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

}

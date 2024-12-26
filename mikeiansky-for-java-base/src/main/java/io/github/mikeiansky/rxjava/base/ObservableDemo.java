package io.github.mikeiansky.rxjava.base;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class ObservableDemo {


    public static void main(String[] args) {
        Observable.just("one", "two", "three", "four", "five")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("accept : " + s);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("complete ... ");
                    }
                });

    }

}

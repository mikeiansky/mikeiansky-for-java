package io.github.mikeiansky.rxjava.base;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class ObservableDemo {

    public static void main(String[] args) {

        Observable.create(emitter -> {
                    while (!emitter.isDisposed()) {
                        long time = System.currentTimeMillis();
                        emitter.onNext(time);
                        if (time % 2 != 0) {
                            emitter.onError(new IllegalStateException("Odd millisecond!"));
                            break;
                        }
                    }
                })
                .subscribe(System.out::println, Throwable::printStackTrace);

    }

}

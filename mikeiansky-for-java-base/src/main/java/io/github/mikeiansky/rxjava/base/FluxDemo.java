package io.github.mikeiansky.rxjava.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/**
 * @author mike ian
 * @date 2024/12/26
 * @desc
 **/
public class FluxDemo {

    public static void main(String[] args) {

        Flux.just("hello")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });

    }

}

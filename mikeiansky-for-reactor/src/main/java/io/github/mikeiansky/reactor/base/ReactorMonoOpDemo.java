package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

/**
 *
 * @author mike ian
 * @date 2025/6/10
 * @desc
 **/
public class ReactorMonoOpDemo {

    public static void main(String[] args) throws InterruptedException {
        Mono.just("Hello")
                .and(Mono.just("World"))
                .map(tuple -> {
                    System.out.println("Combining two monos" + tuple);
                    return tuple;
                })
                .subscribe(System.out::println); // Output: Hello World
        Thread.sleep(1000);
    }

}

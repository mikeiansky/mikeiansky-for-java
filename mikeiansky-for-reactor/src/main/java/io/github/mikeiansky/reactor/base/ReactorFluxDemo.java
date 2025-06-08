package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * @author mike ian
 * @date 2025/6/8
 * @desc
 **/
public class ReactorFluxDemo {

    public static void main(String[] args) {

        System.out.println("use seq1");
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        seq1.subscribe(System.out::println);

        System.out.println("before use seq2 ");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        seq2.subscribe(System.out::println);

    }

}

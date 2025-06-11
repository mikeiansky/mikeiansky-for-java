package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

/**
 *
 * @author mike ian
 * @date 2025/6/10
 * @desc
 **/
public class ReactorFluxOpDemo {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, 4, 5)
                .map(i -> i * 2) // Multiply each element by 2
                .filter(i -> i > 5) // Filter elements greater than 5
                .collectList()
//                .flatMap(integers -> null)
//                .handle((k, v) -> {})
                .subscribe(System.out::println); // Print the resulting elements

    }

}

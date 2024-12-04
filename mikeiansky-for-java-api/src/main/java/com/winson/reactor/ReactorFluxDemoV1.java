package com.winson.reactor;

import reactor.core.publisher.Flux;

/**
 * @author winson
 * @date 2022/6/21
 **/
public class ReactorFluxDemoV1 {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .subscribe(System.out::println);

    }

}

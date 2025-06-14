package io.github.mikeiansky.reactor.base;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author mike ian
 * @date 2025/6/14
 * @desc
 **/
public class MonoContextDemo {

    public static void main(String[] args) {

        String key = "message";
        Mono.just("Hello")
                .flatMap(s -> Mono.deferContextual(ctx -> {
                    System.out.println("ctx : " + ctx);
                    return Mono.just(s + "<-->" + ctx.getOrEmpty(key+"-01"));
                }))
//                .contextWrite(ctx -> ctx.put(key, "World"))
                .contextWrite(ctx -> ctx.put(key, "mike"))
                .subscribe(System.out::println);

    }

}

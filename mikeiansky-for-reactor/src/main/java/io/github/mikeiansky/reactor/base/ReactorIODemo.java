package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mike ian
 * @date 2025/6/13
 * @desc
 **/
public class ReactorIODemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                60,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactory() {
                    private AtomicInteger count = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "test-" + count.getAndIncrement());
                    }
                });

        String result = Mono.just("hello")
                .subscribeOn(Schedulers.fromExecutor(threadPoolExecutor))
                .doOnNext(value->{
                    System.out.println("value: " + value + " , thread name : " + Thread.currentThread().getName());
                })
                .block();
        System.out.println("result1:"+result);

        result = Mono.just("world")
                .subscribeOn(Schedulers.parallel())
                .doOnNext(value->{
                    System.out.println("value: " + value + " , thread name : " + Thread.currentThread().getName());
                })
                .block();
        System.out.println("result2:"+result);

        Flux.just("hello", "world", "mike")
                        .doOnNext(value->{
                            System.out.println("value: " + value + " , thread name : " + Thread.currentThread().getName());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }).subscribeOn(Schedulers.fromExecutor(threadPoolExecutor))
                        .subscribe();
        System.out.println("complete ----> ");
        threadPoolExecutor.shutdown();

    }

}

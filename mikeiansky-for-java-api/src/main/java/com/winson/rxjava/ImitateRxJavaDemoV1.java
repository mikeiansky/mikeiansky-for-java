package com.winson.rxjava;

import com.winson.pojo.Animal;
import com.winson.pojo.Dog;
import com.winson.pojo.Labrador;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author winson
 * @date 2022/6/18
 **/
public class ImitateRxJavaDemoV1 {


    public static <T> Consumer<? super T> create(Consumer<? super T> c) {
        Function<String, ? extends Consumer> f = new Function<String, Consumer>() {
            @Override
            public Consumer<String> apply(String s) {
                return new Consumer<String>() {
                    @Override
                    public void accept(String o) {
                        System.out.println("nest consume accept o : " + o);
                    }
                };
            }
        };
        return f.apply("hello");
    }

    public static void consume(Consumer<? super Dog> consumer){
        System.out.println("consumer is : " + consumer);
//        consumer.accept(new Dog());
        consumer.accept(new Labrador());
    }

    public static void main(String[] args) {

        System.out.println("Hello");

//        Consumer<Dog> cd = new Consumer<Dog>() {
//            @Override
//            public void accept(Dog o) {
//
//            }
//        };
//        ImitateRxJavaDemoV1.consume(ImitateRxJavaDemoV1.create(cd));

//        AtomicReference<Dog> ad = new AtomicReference<>();
//        ad.compareAndSet(null, new Dog());
//        System.out.println(ad.get().hashCode());
////        ad.compareAndSet(ad.get(), new Dog());
//        ad.compareAndSet(null, new Dog());
////        ad.set(new Dog());
//        System.out.println(ad.get().hashCode());

        AtomicInteger ai = new AtomicInteger();
        System.out.println(ai.get());
    }

}

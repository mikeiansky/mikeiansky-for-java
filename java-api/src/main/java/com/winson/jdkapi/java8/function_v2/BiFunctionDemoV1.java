package com.winson.jdkapi.java8.function_v2;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/10/10
 **/
public class BiFunctionDemoV1 {

    public static class Animal {
        public void eat() {
            System.out.println("animal eat");
        }
        public void sleep(){
            System.out.println("animal sleep");
        }
    }

    public static class Cat extends Animal {
        public void eat() {
            System.out.println("cat eat");
        }
        public void catPlay(){
            System.out.println("cat play");
        }
    }

    public static class BigCat extends Cat {
        public void eat() {
            System.out.println("big cat eat");
        }

        public void bigCatRun(){
            System.out.println("big cat run");
        }
    }

    public static void testSuperBiFunction(BiFunction<? super Cat, Object, ?> biFunction, Cat animal) {
        Object result = biFunction.apply(animal, animal);
    }

    public static BiFunction<?, ?, Object> createBi() {
        return new BiFunction<Object, Object, Object>() {
            @Override
            public Object apply(Object o, Object o2) {
                return null;
            }
        };
    }

    public static Supplier<?> createSp(Object object){
        return new Supplier<Object>() {
            @Override
            public Object get() {
                return object;
            }
        };
    }

//    public static void testExtendBi

    public static void main(String[] args) {

        testSuperBiFunction(new BiFunction<Animal, Object, Object>() {
            @Override
            public Object apply(Animal animal, Object o) {
                animal.eat();
                return null;
            }
        }, new Cat());

        Supplier<Supplier<?>> supplier = new Supplier<Supplier<?>>() {
            @Override
            public Supplier<?> get() {
                return new Supplier<Object>() {
                    @Override
                    public Object get() {
                        return new Object();
                    }
                };
            }
        };


        Object obj = new Object();
        Supplier<?> sp = createSp(obj);
        BiFunction<?, ?, Object> bf = createBi();
//        bf.apply(sp.get(),sp.get());

        Object sr = sp.get();
        System.out.println(sr);

        Supplier<? extends Cat> sce = new Supplier<BigCat>() {
            @Override
            public BigCat get() {
                return new BigCat();
            }
        };
        Cat bigCat = sce.get();
        bigCat.eat();

        Supplier<? super Cat> scs = new Supplier<Animal>() {
            @Override
            public Animal get() {
                return new Animal();
            }
        };
        Object animal = scs.get();

        Consumer<? extends Cat> c1 = new Consumer<BigCat>() {
            @Override
            public void accept(BigCat cat) {
                cat.bigCatRun();
            }
        };
//        c1.accept(new BigCat());

    }

}

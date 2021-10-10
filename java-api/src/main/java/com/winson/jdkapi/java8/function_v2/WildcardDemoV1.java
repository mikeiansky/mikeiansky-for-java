package com.winson.jdkapi.java8.function_v2;

import com.winson.pojo.Animal;
import com.winson.pojo.Dog;
import com.winson.pojo.Labrador;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/10/10
 * @desc 通配符测试
 **/
public class WildcardDemoV1 {

    public static void testSuperOnUse(Consumer<? super Dog> consumer, Dog dog) {
        consumer.accept(dog);
    }

    public static Dog testExtendOnUse(Supplier<? extends Dog> supplier) {
        return supplier.get();
    }

    public static void testListSuper(List<? super Dog> dogList){
        dogList.add(new Labrador());
        Object object = dogList.get(0);
        // compiler error
//        Dog dog = dogList.get(0);
    }

    public static void testListExtend(List<? extends Dog> dogList){
        Dog dog = dogList.get(0);
        Animal animal = dogList.get(1);
    }

    public static void main(String[] args) {

        testSuperOnUse(new Consumer<Animal>() {
            @Override
            public void accept(Animal animal) {
                animal.eat();
                animal.sleep();
            }
        }, new Dog());

        Dog dog = testExtendOnUse(new Supplier<Labrador>() {
            @Override
            public Labrador get() {
                return new Labrador();
            }
        });
        dog.eat();

    }

}

package com.winson.jdkapi.juc.base;

import com.winson.pojo.Animal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author winson
 * @date 2022/6/7
 **/
public class AtomicUpdateFieldDemoV1 {

    volatile Animal animal;

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.size = 20;
        AtomicIntegerFieldUpdater<Animal> animalSizeFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Animal.class, "size");
        boolean update = animalSizeFieldUpdater.compareAndSet(animal, 20, 30);
        System.out.println("update result : " + update);
        System.out.println(animal.size);

        AtomicUpdateFieldDemoV1 af = new AtomicUpdateFieldDemoV1();
        af.animal = animal;
        System.out.println("af.animal 1 : " + af.animal);

        AtomicReferenceFieldUpdater<AtomicUpdateFieldDemoV1, Animal> refUpdater = AtomicReferenceFieldUpdater.newUpdater(AtomicUpdateFieldDemoV1.class, Animal.class, "animal");
//        refUpdater.compareAndSet(af, new Animal(), new Animal());
        refUpdater.compareAndSet(af, animal, new Animal());
        System.out.println("af.animal 2 : " + af.animal);

    }

}

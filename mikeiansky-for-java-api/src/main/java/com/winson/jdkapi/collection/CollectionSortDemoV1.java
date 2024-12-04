package com.winson.jdkapi.collection;

import com.winson.pojo.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author winson
 * @date 2021/10/13
 **/
public class CollectionSortDemoV1 {

    public static class MyComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
//            System.out.println("o1 : " + o1 + " , o2 : " + o2 + " , result : " + (o1.size - o2.size));
//            return o1.size - o2.size;
            return o1.size - o2.size;
        }
    }

    public static void main(String[] args) {

        Animal animal1 = new Animal();
        animal1.size = 1;

        Animal animal2 = new Animal();
        animal2.size = 2;

        Animal animal3 = new Animal();
        animal3.size = 3;

        List<Animal> animalList = new ArrayList<>();
        animalList.add(animal2);
        animalList.add(animal1);
        animalList.add(animal3);

//        animalList.sort(new MyComparator());
        // function 1
        Collections.sort(animalList);
        // function 2
        animalList.sort(new MyComparator());

        System.out.println(animalList);

    }

}

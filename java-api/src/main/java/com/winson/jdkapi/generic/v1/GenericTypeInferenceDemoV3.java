package com.winson.jdkapi.generic.v1;

import com.winson.pojo.Animal;
import com.winson.pojo.Dog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winson
 * @date 2021/10/10
 **/
public class GenericTypeInferenceDemoV3 {

    public static <T> T acceptAndReturn(T t) {
        return t;
    }

    public <T> T add(T t1, T t2) {
        return t2;
    }

    public static <T> T addStatic(T t1, T t2) {
        return t2;
    }

    public static <T> T getTarget(Object x) {
        T t = (T) x;
        System.out.println("target : " + t);
        return (T) x;
    }

    public static <T> T getTargetFromList(T... ts) {
        if (ts != null && ts.length > 0) {
            return ts[0];
        }
        return null;
    }

    public static class New {
        public static <K, V> Map map() {
            return new HashMap<K, V>();
        }
    }

    public static void testMap(Map<String, Integer> map) {
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
    }

    public static void main(String[] args) {

        Integer t1 = 1;
        Float t2 = 1.2f;
        Integer t3 = 2;

        GenericTypeInferenceDemoV3 demoV3 = new GenericTypeInferenceDemoV3();
        Number r1 = demoV3.add(t1, t2);
        Integer r2 = demoV3.<Integer>add(t1, t3);
        // compiler error
//        Integer r3 = demoV3.<Integer>add(t1,t2);

        Integer r4 = GenericTypeInferenceDemoV3.addStatic(t1, t3);
        Number r5 = GenericTypeInferenceDemoV3.addStatic(t1, t2);
        Number r6 = GenericTypeInferenceDemoV3.<Float>addStatic(t2, t2);

        Animal animal = GenericTypeInferenceDemoV3.getTarget(new Animal());
        animal.eat();
        animal.sleep();
        Dog dog = GenericTypeInferenceDemoV3.getTarget(new Dog());
        dog.run();
        dog.eat();

        Animal animal2 = GenericTypeInferenceDemoV3.<Animal>getTarget(new Dog());

        Animal animal3 = GenericTypeInferenceDemoV3.getTargetFromList(new Animal(), new Dog());

        Map<String, Integer> map = New.map();
        System.out.println("test map");
        testMap(New.map());
        testMap(New.<String, Integer>map());
        testMap(New.<String, String>map());
        System.out.println("test map ==== ");
    }

}

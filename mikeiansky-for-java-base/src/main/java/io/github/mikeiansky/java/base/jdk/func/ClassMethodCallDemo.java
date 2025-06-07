package io.github.mikeiansky.java.base.jdk.func;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author mike ian
 * @date 2025/6/7
 * @desc
 **/
public class ClassMethodCallDemo {

    public interface HelloCall {
        String call(String name);

        String change(String good);
    }

    public static class One implements HelloCall {
        @Override
        public String call(String name) {
            System.out.println("One say Hello: " + name + ", this : " + this);
            return "Hello " + name;
        }

        @Override
        public String change(String good) {
            System.out.println("One say change: " + good + ", this : " + this);
            return "change " + good;
        }


    }

    public interface ThreeArgFunc<A1, A2, A3> {
        void useThree(A1 a1, A2 a2, A3 a3);
    }

    public static class CallFactory {
        public static void doClazzCall(Supplier<One> supplier, BiFunction<HelloCall, String, String> call, String name) {
            call.apply(supplier.get(), name);
        }

        public static <K, V> Map<K, V> useMap(Supplier<Map<K, V>> supplier,
                                              ThreeArgFunc<Map<K, V>, K, V> threeArgFunc,
                                              K key, V value) {
            System.out.println("Using map with key: " + key + ", value: " + value);
            Map<K, V> map = supplier.get();
            threeArgFunc.useThree(map, key, value);
            return map;
        }
    }

    public static void main(String[] args) {
        CallFactory.doClazzCall(One::new, HelloCall::call, "mike ian");
        CallFactory.doClazzCall(One::new, HelloCall::change, "phone");
        CallFactory.doClazzCall(One::new, HelloCall::call, "ian");
        CallFactory.doClazzCall(One::new, HelloCall::change, "pad");
        Map<Integer,String> result = CallFactory.useMap(HashMap::new, Map::put, 1, "one");
        System.out.println("Resulting map: " + result);
    }

}

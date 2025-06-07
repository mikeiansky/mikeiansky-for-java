package io.github.mikeiansky.java.base.jdk.func;

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

    public static class CallFactory {
        public static void doClazzCall(Supplier<One> supplier, BiFunction<HelloCall, String, String> call, String name) {
            call.apply(supplier.get(), name);
        }
    }

    public static void main(String[] args) {
        CallFactory.doClazzCall(One::new, HelloCall::call, "mike ian");
        CallFactory.doClazzCall(One::new, HelloCall::change, "phone");
        CallFactory.doClazzCall(One::new, HelloCall::call, "ian");
        CallFactory.doClazzCall(One::new, HelloCall::change, "pad");
    }

}

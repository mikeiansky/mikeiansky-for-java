package io.github.mikeiansky.java.base.jdk.func;

import java.util.function.Function;

/**
 * @author mike ian
 * @date 2025/6/7
 * @desc
 **/
public class ObjMethodCallDemo {

    public interface HelloCall {
        String call(String name);
    }

    public static class One implements HelloCall {

        @Override
        public String call(String name) {
            System.out.println("One say Hello: " + name);
            return "hello " + name;
        }

        public String doCall(String name) {
            return CallFactory.doClazzCall(this::call, name);
        }

    }

    public static class CallFactory {
        public static String doClazzCall(Function<String, String> apply, String name) {
            return apply.apply(name);
        }
    }

    public static void main(String[] args) {
        One one = new One();
        System.out.println(one.doCall("one"));
    }

}

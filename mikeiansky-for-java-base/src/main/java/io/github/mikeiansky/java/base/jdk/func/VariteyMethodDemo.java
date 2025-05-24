package io.github.mikeiansky.java.base.jdk.func;

import java.util.Arrays;

/**
 * @author mike ian
 * @date 2025/5/24
 * @desc
 **/
public class VariteyMethodDemo {

    public static void use(Object... args){
        System.out.println("args: " + args);
        System.out.println("------- items");
        Arrays.stream(args).forEach(System.out::println);
    }

    public static void main(String[] args) {
        String[] as1 = new String[]{"a", "b", "c"};
        use(as1);
        System.out.println("use2 ----------");
        use("2", "a", "b", "c");
    }

}

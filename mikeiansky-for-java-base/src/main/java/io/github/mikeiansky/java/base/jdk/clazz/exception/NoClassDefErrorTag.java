package io.github.mikeiansky.java.base.jdk.clazz.exception;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class NoClassDefErrorTag {

    static {
        System.out.println("NoClassDefTag initialized");
        if (true) {
            throw new RuntimeException("NoClassDefTag initialized");
        }
    }
    public static void hello(){
        System.out.println("hello NoClassDefTag");
    }

}

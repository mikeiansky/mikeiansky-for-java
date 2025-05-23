package io.github.mikeiansky.java.base.jdk.clazz.exception;

import java.io.IOException;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class NoClassDefErrorDemo {

    public static void main(String[] args) throws IOException {
        String clazzName = NoClassDefErrorTag.class.getName();
        System.out.println("clazzName: " + clazzName);
        System.out.println("first hello");
        try {
            NoClassDefErrorTag.hello();
        } catch (Error e) {
            e.printStackTrace();
        }

        System.out.println("second hello");
        try {
            NoClassDefErrorTag tag = new NoClassDefErrorTag();
//            NoClassDefTag.hello();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

}

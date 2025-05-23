package io.github.mikeiansky.java.base.jdk.clazz.exception;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClassNotFoundException {

    public static void main(String[] args) throws java.lang.ClassNotFoundException {
        System.out.println(Class.forName("hello"));
    }

}

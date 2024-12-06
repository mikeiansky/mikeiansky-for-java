package io.github.mikeiansky.java.base.jdk.reflect.base;

/**
 * @author mike ian
 * @date 2024/12/5
 * @desc
 **/
public class AssignableFromDemo {

    public static class Father {

    }

    public static class Son extends Father {

    }

    public static void main(String[] args) {
        System.out.println(Son.class.isAssignableFrom(Father.class));
        System.out.println(Father.class.isAssignableFrom(Son.class));
    }

}

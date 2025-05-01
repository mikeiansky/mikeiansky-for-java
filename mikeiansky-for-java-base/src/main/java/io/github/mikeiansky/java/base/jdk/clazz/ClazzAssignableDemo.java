package io.github.mikeiansky.java.base.jdk.clazz;

/**
 * @author mike ian
 * @date 2025/4/29
 * @desc
 **/
public class ClazzAssignableDemo {

    public static class One {

    }

    public static class Two extends One {

    }

    public static class Three extends Two {

    }

    public static void main(String[] args) {

        System.out.println(One.class.isAssignableFrom(Two.class));
        System.out.println(One.class.isAssignableFrom(Three.class));
        System.out.println(Two.class.isAssignableFrom(One.class));
        System.out.println(Three.class.isAssignableFrom(One.class));

    }

}

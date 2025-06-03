package io.github.mikeiansky.java.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author mike ian
 * @date 2025/6/3
 * @desc
 **/
public class UnsafeObjDemo {

    static Unsafe unsafe;

    static {
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Tag {

    }

    public static class Son {
        private int age;
        private int height;
        private String name;
        private long money;
        private volatile int volatileAge;
        private byte body;
        private double salary;
        private Integer id;
        private Double friends;
        private Boolean isMarried;
        private Tag tag1;
        private Tag tag2;
        private Tag tag3;
        private static String version;
        private static int count;
        private static Tag staticTag;
        private int status;
    }

    public static void main(String[] args) {

        System.out.println("Unsafe instance: " + unsafe);
        System.out.println("=============> ");
        for (Field declaredField : Son.class.getDeclaredFields()) {
            boolean staticField = (declaredField.getModifiers() & Modifier.STATIC) != 0;
//            if (staticField) {
//                // Skip static fields
//                continue;
//            }
            System.out.println(
                    (staticField ? "static " : "no static - ")
                            + "【" + declaredField.getName() + "】"
                            + " - type: " + declaredField.getType().getName()
                            + ", offset: "
                            + (staticField ? unsafe.staticFieldOffset(declaredField) : unsafe.objectFieldOffset(declaredField))
            );
        }


    }

}

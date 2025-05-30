package com.winson.netty.v2.jdk;

import io.netty.util.internal.ReflectionUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class UnsafeJdkDemo {
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

    public static void main(String[] args) {

        byte[] data = new byte[]{0, 0, 0, 11, 0, 0, 0, 22};
//        int b1 = unsafe.getInt(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + 3);
//        System.out.println("b1 is " + b1);
        int b2 = unsafe.getInt(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + 5);
        System.out.println("b2 is " + b2);

    }

}

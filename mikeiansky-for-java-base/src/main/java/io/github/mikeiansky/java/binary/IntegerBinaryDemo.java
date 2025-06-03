package io.github.mikeiansky.java.binary;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteOrder;

/**
 * @author mike ian
 * @date 2025/6/3
 * @desc
 **/
public class IntegerBinaryDemo {

    public static String to32Str(String value) {
        return String.format("%32s", value).replace(' ', '0');
    }

    public static void main(String[] args) {

        System.out.println("本机字节序: " + ByteOrder.nativeOrder());
        System.out.println("大端字节序常量: " + ByteOrder.BIG_ENDIAN);
        System.out.println("小端字节序常量: " + ByteOrder.LITTLE_ENDIAN);

        // 示例：判断本机字节序
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            System.out.println("当前是大端字节序");
        } else {
            System.out.println("当前是小端字节序");
        }

//        Integer value = Integer.valueOf(5);
//        System.out.println(to32Str(Integer.toBinaryString(value)));
//        System.out.println(to32Str(Integer.toBinaryString(Integer.reverse(value))));

        int data = 0x123;
        System.out.println(data);
        System.out.println(Integer.reverse(data));
        System.out.println(Integer.reverseBytes(data));
        System.out.println(to32Str(Integer.toBinaryString(data)));
        System.out.println(to32Str(Integer.toBinaryString(Integer.reverse(data))));
        System.out.println(to32Str(Integer.toBinaryString(Integer.reverseBytes(data))));


    }

}

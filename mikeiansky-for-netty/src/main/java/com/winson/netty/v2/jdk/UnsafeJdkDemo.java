package com.winson.netty.v2.jdk;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

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

    public static class Person {
        private int age;
        private int height;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {

        byte[] data = new byte[]{22, 0, 0, 13, 0, 0, 0, 22};
        int b1 = unsafe.getByte(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + 0);
        System.out.println("b1 is " + b1);
        int b2 = unsafe.getInt(data, Unsafe.ARRAY_BYTE_BASE_OFFSET + 3);
        System.out.println("b2 is " + b2);
        System.out.println(Unsafe.ARRAY_BYTE_BASE_OFFSET);

        Person person = new Person();
        person.setAge(183);
        person.setHeight(180);

        long ageOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("age"));
        long heightOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("height"));

        System.out.println("ageOffset : " + ageOffset);
        System.out.println("heightOffset : " + heightOffset);

        int age = unsafe.getInt(person, ageOffset);
        int height = unsafe.getInt(person, heightOffset);

        System.out.println("person age : " + age);
        System.out.println("person height : " + height);


    }

}

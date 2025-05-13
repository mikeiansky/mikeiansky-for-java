package io.github.mikeiansky.java.base.jdk.arrays;

/**
 * @author mike ian
 * @date 2025/5/13
 * @desc
 **/
public class CreateArrayDemo {

    public static void main(String[] args) {

        Object[] obj = new Object[3];
        System.out.println(obj.length);
        System.out.println(obj);

        for (Object o : obj) {
            System.out.println(o);
        }

    }

}

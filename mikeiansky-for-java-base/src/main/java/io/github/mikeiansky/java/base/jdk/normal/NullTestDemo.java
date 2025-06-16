package io.github.mikeiansky.java.base.jdk.normal;

/**
 *
 * @author mike ian
 * @date 2025/6/16
 * @desc
 **/
public class NullTestDemo {

    public static void main(String[] args) {
        Long l1 = 11111111l;
        Long l2 = null;
        Long l3 = 11111111l;
        System.out.println(l1 == l2);
        System.out.println(l1.equals(l3));
    }

}

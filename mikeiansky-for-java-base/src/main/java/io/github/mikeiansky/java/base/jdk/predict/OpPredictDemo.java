package io.github.mikeiansky.java.base.jdk.predict;

/**
 * @author mike ian
 * @date 2025/4/29
 * @desc
 **/
public class OpPredictDemo {

    public static void main(String[] args) {

        boolean p1 = false;
        boolean p2 = true;
        boolean p3 = false;

        if (p1 || p2 && p3) {
            System.out.println("p1 || p2 || p3");
        }

    }

}

package io.github.mikeiansky.java.base.jdk.predict;

/**
 * @author mike ian
 * @date 2024/12/6
 * @desc
 **/
public class ComplexLogicPredictDemo {

    public static void main(String[] args) {

        boolean condition1 = true;
        boolean condition2 = false;
        boolean condition3 = true;

        if (condition1 || condition2 && condition3) {
            System.out.println("hit condition");
        }

    }

}

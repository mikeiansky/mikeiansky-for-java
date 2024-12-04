package com.winson.jdkapi.math;

/**
 * @author Mike Ian
 * @date 2022/11/15
 **/
public class MathCalcDemoV1 {

    public static void main(String[] args) {

        //0.93162927369286291629941864748035
//        0.30102999566398119521373889472449

        double N = 260952;
        double n = 30544;
        double temp = 1 + (N - n + 0.5) / (n + 0.5);
        System.out.println("temp ::: "+temp);
        double r = Math.log(1 + (N - n + 0.5) / (n + 0.5));
        System.out.println("r :: "+r);
        System.out.println("temp :: "+Math.log(temp));
        System.out.println(Math.log(8));
        System.out.println(Math.log(7.5));

//        System.out.println(Math.log10(temp)/Math.log10(2));

    }

}

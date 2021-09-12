package com.winson.jdkapi.common;

/**
 * @author winson
 * @date 2021/9/12
 **/
public class DataRangeDemoV1 {

    public static void main(String[] args) {

        System.out.println(Float.MAX_VALUE);
        System.out.println(Float.MIN_NORMAL);
        System.out.println(Float.MIN_VALUE);
        System.out.println(Float.MIN_EXPONENT);

//        float f1 = -100.0f;
//        float f2 = -200.0f;
//        float f3 = f1 + f2;
//        System.out.println(f3);
//        System.out.println(Float.MIN_VALUE > f3);

        int maxV = Integer.MAX_VALUE;
        int minV = Integer.MIN_VALUE;
        System.out.println(maxV);
        System.out.println(minV);
//        int ov = maxV + 1;
//        System.out.println(ov);

        Float mf = -Float.MAX_VALUE;
        System.out.println(mf);
        Float of = mf + 1000000000000000000000f;
        System.out.println(of);
        float ff1 = 16777215;
        System.out.println(ff1);
        float ff2 = 16777216 * 2;
        System.out.println(ff2);
//        System.out.println(2 ^ 25);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);


    }

}

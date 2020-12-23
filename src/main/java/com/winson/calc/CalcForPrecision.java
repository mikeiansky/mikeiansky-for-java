package com.winson.calc;

import java.math.BigDecimal;

/**
 * @author com.winson
 * @date 2020/12/10
 * @desc 精度测试类
 **/
public class CalcForPrecision {

    public static void main(String[] args) {

        Double a = 19.99;
        Double c = 20d;
        BigDecimal b1 = new BigDecimal(Double.toString(a));
        BigDecimal b2 = new BigDecimal(Double.toString(c));
        System.out.println(b1.add(b2));

        BigDecimal ab = new BigDecimal(a.toString());
        BigDecimal cb = new BigDecimal(c.toString());
        System.out.println(19.99+20);
        System.out.println("---");
        System.out.println(ab.doubleValue());
        BigDecimal r1 = ab.add(cb);
        System.out.println(r1.doubleValue());
        BigDecimal r2 = r1.add(cb);
        System.out.println(r2.doubleValue());
        System.out.println("---");
        System.out.println(1.0-0.66);
        System.out.println(0.033*100);
        System.out.println(12.3/100);

    }

}

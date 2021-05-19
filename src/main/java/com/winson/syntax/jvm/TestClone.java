package com.winson.syntax.jvm;

import sun.misc.Unsafe;

/**
 * @author winson
 * @date 2021/5/19
 **/
public class TestClone implements Cloneable{

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(" test clone start ... ");

        TestClone testClone = new TestClone();
        testClone.count = 20;
        TestClone tc = (TestClone) testClone.clone();
        System.out.println("testClone : "+testClone);
        System.out.println("tc:"+tc);
        System.out.println("tc.count:" + tc.count);
        System.out.println("testClone.count:" + testClone.count);
        System.out.println(" test clone end ... ");
    }

}

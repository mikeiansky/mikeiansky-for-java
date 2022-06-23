package com.winson.jvm.initialize;

/**
 * @author winson
 * @date 2022/6/23
 **/
public class InitBeforeStaticDemoV1 {

    public static int staticFlag;


    public int flag;

    public InitBeforeStaticDemoV1(){
        System.out.println("--------instance ------- init");
        System.out.println("staticFlag:" + staticFlag);
        flag = 101;
    }

    public static final InitBeforeStaticDemoV1 INSTANCE = new InitBeforeStaticDemoV1();

    static {
        System.out.println("--------class ------- init");
        staticFlag = 102;
        System.out.println("instanceFlag:"+INSTANCE.flag);
    }


    public static void main(String[] args) {

        InitBeforeStaticDemoV1 iv = InitBeforeStaticDemoV1.INSTANCE;

    }

}

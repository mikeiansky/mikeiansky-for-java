package com.winson.jvm.analysis;

/**
 * @author winson
 * @date 2021/8/31
 **/
public class InitializationDemoV1 {

    public static class MyBean{
        static {
            System.out.println(" my bean initial .... ");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        InitializationDemoV1 v1 = new InitializationDemoV1();
        System.out.println(MyBean.class.getName());
        System.out.println(MyBean.class);
        System.out.println(v1);
//        Class clazz = Class.forName("com.winson.jvm.analysis.InitializationDemoV1$MyBean");
//        System.out.println(clazz);

        MyBean[] myBeans = new MyBean[10];
//        System.out.println(myBeans[1]);
//        System.out.println(myBeans);

        Class ac = Class.forName("Lcom.winson.jvm.analysis.InitializationDemoV1$MyBean");
        System.out.println(ac);
    }

}

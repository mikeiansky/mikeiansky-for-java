package com.winson.jvm.classloader;

/**
 * @author winson
 * @date 2021/8/26
 **/
public class ClassInitialDemoV1 {

    public static final ClassInitialDemoV1 instance = new ClassInitialDemoV1();

    public int a;

    static {
        System.out.println("class initial demo init ... ");
        System.out.println("class initial demo init instance... : " + instance);
        System.out.println("class initial demo init instance.a ... : " + instance.a);
    }

    public ClassInitialDemoV1(){
        a = 30;
        System.out.println("class init demo class construct ... ");
    }

    public static class NestClass{
        public static final int FLAG = 20;
        static {
            System.out.println("nest class initial ... ");
        }
    }

    public static void main(String[] args) {

        System.out.println("Hello World!");
        System.out.println("nest class flag : " + NestClass.FLAG);
        NestClass[] nc = new NestClass[10];
        System.out.println("nest class array : " + nc);
        System.out.println("instance obj : " + ClassInitialDemoV1.instance);
        System.out.println("instance obj : " + ClassInitialDemoV1.instance);
    }

}

package com.winson.jvm.classloader;

/**
 * @author winson
 * @date 2021/10/23
 **/
public class ClassInitialByOtherDemoV1 {

    static {
        System.out.println("ClassInitialByOtherDemoV1 class instantiate ... ");
    }

    public static class InnerStaticClass{
        static {
            System.out.println("InnerStaticClass class instantiate ... ");
        }
    }

    // 这里加载类的时候就验证
    public static void playByStatic(InnerStaticClass isc){

    }

    // 这里加载类的时候就会验证
    public void playByVirtual(InnerStaticClass isc){

    }

    public void playInit(){
        // 这里运行的时候才会进行验证
        System.out.println(InnerStaticClass.class);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ClassInitialByOtherDemoV1 dv1 = new ClassInitialByOtherDemoV1();
        System.out.println(dv1);

    }

}

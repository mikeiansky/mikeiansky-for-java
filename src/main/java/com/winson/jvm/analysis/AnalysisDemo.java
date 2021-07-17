package com.winson.jvm.analysis;

/**
 * @author winson
 * @date 2021/7/17
 **/
public class AnalysisDemo {

    static {
        a= 1;
//        System.out.println(a);
    }

    static int a = 0;

    interface Interface0 {
        int A = 0;
    }
    interface Interface1 extends Interface0 {
        int A = 1;
    }
    interface Interface2 {
        int A = 2;
    }
    static class Parent implements Interface1 {
        public static int A = 3;
    }
    static class Sub extends Parent implements Interface2 {
        public static int A = 4;
    }
    public static void main(String[] args) {
        System.out.println(Sub.A);
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(System.getProperty("classpath"));
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };
        System.out.println(myClassLoader);
        System.out.println(myClassLoader.getParent());


    }

}

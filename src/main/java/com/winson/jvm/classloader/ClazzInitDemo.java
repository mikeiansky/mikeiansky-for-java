package com.winson.jvm.classloader;

/**
 * @author winson
 * @date 2021/8/4
 **/
public class ClazzInitDemo {

    public static class LazyHolder{
        public static final ClazzInitDemo holder = new ClazzInitDemo();
        static {
            System.out.println("LazyHolder cinit<>");
        }
    }

    public static ClazzInitDemo getInstance(){
        return LazyHolder.holder;
    }

    public static void main(String[] args) {
//        LazyHolder[] lazyHolder = new LazyHolder[2];
//        System.out.println(lazyHolder.getClass());
//        System.out.println(getInstance());

//        int[] arr = new int[10];
//        System.out.println(arr.getClass());

        try {
            Class clazz1 = Class.forName("[I");
            System.out.println("clazz1 : "+clazz1);
            Class clazz2 = Class.forName("[Lcom.winson.jvm.classloader.ClazzInitDemo$LazyHolder;");
            System.out.println("clazz2 : "+clazz2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

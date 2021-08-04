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
        LazyHolder[] lazyHolder = new LazyHolder[2];
        System.out.println(getInstance());

    }

}

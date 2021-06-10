package com.winson.syntax.jvm.classloader;

/**
 * @author winson
 * @date 2021/6/10
 **/
public class TestSingleton {

    public static class LazyHolder {
        static final TestSingleton INSTNACE = new TestSingleton();

        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static Object getInstance(boolean flag){
        if(flag){
            return new LazyHolder[2];
        }
        return LazyHolder.INSTNACE;
    }

    public static void main(String[] args) {
        System.out.println("test singleton start ... ");
        getInstance(true);
        System.out.println("test singleton stop ... ");
        getInstance(false);
    }

}

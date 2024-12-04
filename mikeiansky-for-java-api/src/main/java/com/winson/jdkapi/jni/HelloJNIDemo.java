package com.winson.jdkapi.jni;

/**
 * @author mike ian
 * @date 2023/9/5
 * @desc
 **/
public class HelloJNIDemo {

    static {
        System.loadLibrary("hello");
    }

    private native void sayHello();

    public static void main(String[] args) {
        HelloJNIDemo jniDemo = new HelloJNIDemo();
        jniDemo.sayHello();
    }

}

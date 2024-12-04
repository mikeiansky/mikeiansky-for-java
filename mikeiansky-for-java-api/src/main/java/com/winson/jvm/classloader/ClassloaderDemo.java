package com.winson.jvm.classloader;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ClassloaderDemo {

    public static void main(String[] args) {
        System.out.println("String::classloader : " + String.class.getClassLoader());
        System.out.println("ClassloaderDemo::classloader : " + ClassloaderDemo.class.getClassLoader());
        System.out.println("ClassloaderDemo::classloader::parent :" + ClassloaderDemo.class.getClassLoader().getParent());
        System.out.println("Exception::classloader : " + Exception.class.getClassLoader());
        System.out.println("Thread::currentClassLoader : " + Thread.currentThread().getContextClassLoader());
    }

}

package com.winson.utils.resource;


import java.io.File;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class ClassResourceDemo {

    public static void main(String[] args) {
        System.out.println("class resource demo start ... ");
        ClassResourceDemo demo = new ClassResourceDemo();
        System.out.println(new File(demo.getClass().getResource("").getFile()).exists());
        System.out.println("current class resource '' info : " + demo.getClass().getResource(""));
        System.out.println("current class resource '/' info : " + demo.getClass().getResource("/"));
        System.out.println("current class loader resource '' info : " + demo.getClass().getClassLoader().getResource(""));
        System.out.println("current class loader resource '/' info : " + demo.getClass().getClassLoader().getResource("/"));
        System.out.println("classloader system resource '' info : " + ClassLoader.getSystemResource(""));
        System.out.println("classloader system resource '/' info : " + ClassLoader.getSystemResource("/"));
        System.out.println("system user.dir info : " + System.getProperty("user.dir"));
        System.out.println("class resource demo end ... ");
    }

}

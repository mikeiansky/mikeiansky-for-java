package com.winson.utils.resource;

import java.net.URL;

/**
 * @author Mike Ian
 * @date 2023/3/9
 **/
public class ClassLoaderDemo {

    public static void main(String[] args) {

        URL res = ClassLoader.getSystemResource("com\\winson\\utils\\resource\\ClassLoaderDemo.class");
        System.out.println(res);

    }

}

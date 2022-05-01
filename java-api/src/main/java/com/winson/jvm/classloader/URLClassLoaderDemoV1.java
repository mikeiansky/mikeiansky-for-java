package com.winson.jvm.classloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author winson
 * @date 2022/5/1
 **/
public class URLClassLoaderDemoV1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        URLClassLoader urlClassLoader1 = new URLClassLoader(new URL[]{new URL("file:D:/work/temp/")});
        URLClassLoader urlClassLoader2 = new URLClassLoader(new URL[]{new URL("file:D:/work/temp/")});
//        URLClassLoader urlClassLoader2 = new URLClassLoader(new URL[]{new URL("file:D:/work/temp/")},urlClassLoader1);
        Class tempClazz1 = urlClassLoader1.loadClass("com.winson.Temp");
        Class tempClazz2 = urlClassLoader2.loadClass("com.winson.Temp");
        System.out.println(tempClazz1);
        System.out.println(tempClazz2);
        System.out.println(tempClazz1 == tempClazz2);
        System.out.println("---------");
        // 这里是classpath中的类
        Class demoClazz1 = urlClassLoader1.loadClass(URLClassLoaderDemoV1.class.getName());
        Class demoClazz2 = urlClassLoader2.loadClass(URLClassLoaderDemoV1.class.getName());
        System.out.println(demoClazz1 == demoClazz2);
        Class tempClazz11 = urlClassLoader1.loadClass("com.winson.Temp");
        System.out.println(tempClazz1 == tempClazz11);

    }

}

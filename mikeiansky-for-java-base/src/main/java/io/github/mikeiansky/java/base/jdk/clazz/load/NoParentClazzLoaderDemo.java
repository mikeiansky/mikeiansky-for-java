package io.github.mikeiansky.java.base.jdk.clazz.load;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author mike ian
 * @date 2025/6/6
 * @desc
 **/
public class NoParentClazzLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String cn = NoParentClazzLoaderDemo.class.getName();
        System.out.println(cn);
        System.out.println(NoParentClazzLoaderDemo.class.getClassLoader());
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[0]);
        Class cz = urlClassLoader.loadClass(cn);
        System.out.println(cz.getClassLoader());
        System.out.println(urlClassLoader.getParent());
        System.out.println(ClassLoader.getSystemClassLoader());

    }

}

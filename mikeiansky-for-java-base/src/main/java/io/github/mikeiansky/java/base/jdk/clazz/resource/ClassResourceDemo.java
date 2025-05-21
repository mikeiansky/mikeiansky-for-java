package io.github.mikeiansky.java.base.jdk.clazz.resource;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClassResourceDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=========> ClassResourceDemo");
        String clazzName = ClassResourceDemo.class.getName();
        System.out.println("clazzName: " + clazzName);

        String resourcePath = clazzName.replace('.', '/') + ".class";
        System.out.println("resourcePath: " + resourcePath);

        ClassLoader classLoader = ClassResourceDemo.class.getClassLoader();
        URL resourceURL = classLoader.getResource(resourcePath);
        System.out.println("resourceURL: "+resourceURL);

        System.out.println("\n=========> String");
        String stringResourcePath = String.class.getName().replace('.', '/') + ".class";
        System.out.println("stringResourcePath: " + stringResourcePath);
        URL stringResourceUrl = classLoader.getResource(stringResourcePath);
        System.out.println("stringResourceUrl: "+stringResourceUrl);

    }

}

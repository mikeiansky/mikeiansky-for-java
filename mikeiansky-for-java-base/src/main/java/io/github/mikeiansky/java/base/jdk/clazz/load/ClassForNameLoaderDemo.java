package io.github.mikeiansky.java.base.jdk.clazz.load;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClassForNameLoaderDemo {

    public static void main(String[] args) throws Exception {
//        String className = ClassLoaderOfClassForName.class.getName();
//        System.out.println("className: "+className);
//        Class clazzOne = Class.forName(className);
//        System.out.println(clazzOne == ClassLoaderOfClassForName.class);
//        ClassLoaderOfClassForName.hello("test");

        String className = "io.github.mikeiansky.java.base.jdk.clazz.load.ClassLoaderOfClassForName";

        String resourcePath = className.replace('.', '/') + ".class";
        System.out.println("resourcePath: " + resourcePath);
//        Class resourceClazz = ClassForNameLoaderDemo.class.getClassLoader().loadClass(resourcePath);

//        String urlDir = "C:/work/github/mikeiansky-for/mikeiansky-for-java/mikeiansky-for-java-base/target/classes";
        String urlDir = "C:\\Users\\zhouw\\Desktop\\temp\\temp3\\classes";
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {new File(urlDir).toURL()});

//        Enumeration<URL> urlEnumeration = ClassForNameLoaderDemo.class.getClassLoader().getResources(resourcePath);
//        while (urlEnumeration.hasMoreElements()) {
//            URL url = urlEnumeration.nextElement();
//            System.out.println("url: " + url);
            Class clazz = urlClassLoader.loadClass(className);
            System.out.println("URLClassLoader class: " + clazz);
            System.out.println("clazz.getClassLoader() : "+clazz.getClassLoader());
//            System.out.println(clazz == ClassLoaderOfClassForName.class);
//            System.out.println(clazz.equals(ClassLoaderOfClassForName.class));

            clazz.getMethod("hello", new Class[]{String.class}).invoke(clazz, new String[]{"invoke"});

//        }

    }

}

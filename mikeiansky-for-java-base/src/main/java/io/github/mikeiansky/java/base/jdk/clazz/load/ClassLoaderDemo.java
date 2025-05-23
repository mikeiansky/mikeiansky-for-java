package io.github.mikeiansky.java.base.jdk.clazz.load;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoader currentClassLoader = ClassLoaderDemo.class.getClassLoader();
        while (currentClassLoader != null) {
            System.out.println(currentClassLoader);
            currentClassLoader = currentClassLoader.getParent();
        }
        System.out.println("String.class.getClassLoader() : " + String.class.getClassLoader());
        System.out.println("ClassLoaderDemo.class.getClassLoader() : " + ClassLoaderDemo.class.getClassLoader());
    }

}

package io.github.mikeiansky.java.base.jdk.clazz.load;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClasspathClazz {

    static {
        System.out.println("ClasspathClazz initialized");
    }

    public static void hello() {
        System.out.println("hello classpath clazz");
        System.out.println("classpath clazz class load: " + ClasspathClazz.class.getClassLoader());
    }

}

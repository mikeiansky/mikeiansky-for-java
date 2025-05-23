package io.github.mikeiansky.java.base.jdk.clazz.load;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class StaticClazzObj {

    static {
        System.out.println("StaticClazzObj.class.getName() : " + StaticClazzObj.class.getName());
        System.out.println("StaticClazzObj.class.getClassLoader() : " + StaticClazzObj.class.getClassLoader());
    }

    public static void hello() {
        System.out.println("hello StaticClazzObj");
        System.out.println("hello StaticClazzObj.class.getClassLoader() : " + StaticClazzObj.class.getClassLoader());
    }

}

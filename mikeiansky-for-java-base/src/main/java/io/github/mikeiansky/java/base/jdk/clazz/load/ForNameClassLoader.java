package io.github.mikeiansky.java.base.jdk.clazz.load;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ForNameClassLoader {

    static {
        System.out.println("static ForNameClassLoader.class : " + ForNameClassLoader.class);
        System.out.println("static ForNameClassLoader.class.getClassLoader() : " + ForNameClassLoader.class.getClassLoader());
    }

    public static void printLoader(){
        System.out.println("printLoader  ForNameClassLoader.class.getClassLoader() :: " + ForNameClassLoader.class.getClassLoader());
    }

}

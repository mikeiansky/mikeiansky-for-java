package io.github.mikeiansky.java.base.jdk.clazz.load;


/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClassLoaderOfClassForName {

    public static void hello(String msg) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("thread context classloader: " + classLoader);
        System.out.println("ClassLoaderOfClassForName.class.getClassLoader(): " + ClassLoaderOfClassForName.class.getClassLoader());
        System.out.println("hello from ClassLoaderOfClassForName, msg : " + msg);

        System.out.println("===== ForNameClassLoader.class");
        String forNameClassName = ForNameClassLoader.class.getName();
        System.out.println("ForNameClassLoader.class.getName() : " + forNameClassName);
        Class clazz = Class.forName(forNameClassName);
        System.out.println("clazz : " + clazz);
        System.out.println("clazz.getClassLoader() : " + clazz.getClassLoader());
        clazz.getMethod("printLoader").invoke(null);

        System.out.println("===== ClasspathClazz.class");
        String classpathClazzName = "io.github.mikeiansky.java.base.jdk.clazz.load.ClasspathClazz";
        System.out.println("ClasspathClazz.class.getName() : " + classpathClazzName);
        Class<?> classpathClazz = Class.forName(classpathClazzName);
        System.out.println("classpathClazz: " + classpathClazz);
        System.out.println("classpathClazz.getClass(): " + classpathClazz.getClass());
        System.out.println("classpathClazz.getClass().getClassLoader() : " + classpathClazz.getClass().getClassLoader());
        classpathClazz.getMethod("hello").invoke(null);

    }

}

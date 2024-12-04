package com.winson.jvm.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author winson
 * @date 2022/4/28
 **/
public class ClassloaderDemoV1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        System.out.println(ClassloaderDemoV1.class.getName());
        ClassLoader appClassLoader = ClassloaderDemoV1.class.getClassLoader();
        System.out.println("resource1: " + appClassLoader.getResource(""));
        System.out.println("resource2 : " + appClassLoader.getResource("/"));
        URL url = new URL("file:D:/bsh-1.3.0.jar");
        URL d = new URL("file:D:/");
        System.out.println(new File(url.getFile()).exists());
        ClassLoader loader1 = new URLClassLoader(new URL[]{url});
        ClassLoader loader2 = new URLClassLoader(new URL[]{d});
//        System.out.println("loader1 resource1 : " + loader1.getResource(""));
//        System.out.println("loader1 resource2 : " + loader1.getResource("/"));
//        System.out.println(ClassloaderDemoV1.class.getName());

        Class helloClazz = loader2.loadClass("com.winson.jvm.classloader.Hello");
        System.out.println(helloClazz);
        Method method = helloClazz.getMethod("sayHello",new Class[]{});
        method.invoke(helloClazz, new Object[]{});
        Object test = loader2.loadClass("bsh.servlet.BshServlet");
        System.out.println("test:"+test);

//        Class clazz = Hello.class;
//        System.out.println(helloClazz);
//        System.out.println(clazz.equals(helloClazz));

//        Enumeration<URL> urls =  ClassLoader.getSystemResources("bsh.servlet.BshServlet");
//        System.out.println(urls.hasMoreElements());
//        System.out.println("resource : "+loader2.getResource("D:/bsh.servlet.BshServlet"));
////        Servlet servlet = null;
//        System.out.println("forName : "+Class.forName("bsh.servlet.BshServlet", false,
//                loader1));
//
//        Class tc = loader2.loadClass("Test");
//        try {
//            Method method = tc.getMethod("main", new Class[]{String[].class});
//            System.out.println("find method : " + method);
//            method.invoke(tc, new Object[]{new String[]{"haha"}});
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(loader2.loadClass("Test"));
//
////        Object test = loader1.loadClass("bsh.servlet.BshServlet");
////        System.out.println("test:"+test);
//        Object one = loader1.loadClass(ClassloaderDemoV1.class.getName());
//        Object two = loader2.loadClass(ClassloaderDemoV1.class.getName());
//        Object three = loader2.loadClass(ClassloaderDemoV1.class.getName());
//        Object four = appClassLoader.loadClass(ClassloaderDemoV1.class.getName());
//        Object five = appClassLoader.loadClass(ClassloaderDemoV1.class.getName());
//        System.out.println("one:" + one);
//        System.out.println("two:" + two);
//        System.out.println("three:" + three);
//        System.out.println("four:" + four);
//        System.out.println("five:" + five);
//        System.out.println("one.equals(two):" + one.equals(two));
//        System.out.println("two.equals(three):" + two.equals(three));
//        System.out.println("four.equals(five):" + four.equals(five));


    }

}

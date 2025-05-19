package io.github.mikeiansky.java.base.jdk.clazz.load;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @author mike ian
 * @date 2025/5/19
 * @desc
 **/
public class CustomClazzLoad {

    public static void main(String[] args) throws Exception {
        String tag = "one";
//        String clazzName = LoadTag.class.getName();
//
//        // 创建自定义ClassLoader
//        ClassLoader classLoader = new ClassLoader() {
//            @Override
//            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//                System.out.println("Custom ClassLoader loading: " + name);
//                return super.loadClass(name, resolve);
//            }
//        };
//
//        // 使用默认类加载器创建实例
//        LoadTag tagOne = new LoadTag(tag);
//        LoadTag tagTwo = new LoadTag(tag);
//
//        // 使用线程上下文类加载器创建实例（通常与默认类加载器相同）
//        Class<?> loadTagClass = Thread.currentThread().getContextClassLoader().loadClass(clazzName);
//        LoadTag tagThree = (LoadTag) loadTagClass.getConstructor(new Class[]{String.class}).newInstance(tag);
//
//        // 使用自定义ClassLoader创建实例
//        Class<?> customLoadTagClass = classLoader.loadClass(clazzName);
//        LoadTag tagFour = (LoadTag) customLoadTagClass.getConstructor(new Class[]{String.class}).newInstance(tag);
//
//        // 测试equals方法
//        System.out.println("tagOne.equals(tagTwo): " + tagOne.equals(tagTwo)); // 应该为true
//        System.out.println("tagOne.equals(tagThree): " + tagOne.equals(tagThree)); // 应该为true
//        System.out.println("tagTwo.equals(tagThree): " + tagTwo.equals(tagThree)); // 应该为true
//        System.out.println("tagOne.equals(tagFour): " + tagOne.equals(tagFour)); // 可能为false
//        System.out.println("tagTwo.equals(tagFour): " + tagTwo.equals(tagFour)); // 可能为false
//
//        // 更重要的测试：检查类是否相同
//        System.out.println("\nClass comparison:");
//        System.out.println("tagOne.getClass() == tagTwo.getClass(): " +
//                (tagOne.getClass() == tagTwo.getClass())); // true
//        System.out.println("tagOne.getClass() == tagThree.getClass(): " +
//                (tagOne.getClass() == tagThree.getClass())); // true
//        System.out.println("tagOne.getClass() == tagFour.getClass(): " +
//                (tagOne.getClass() == tagFour.getClass())); // false - 关键测试
//
//        // 检查类加载器
//        System.out.println("\nClassLoader comparison:");
//        System.out.println("tagOne.getClass().getClassLoader(): " + tagOne.getClass().getClassLoader());
//        System.out.println("tagFour.getClass().getClassLoader(): " + tagFour.getClass().getClassLoader());
//
//        Enumeration<URL> enumeration = classLoader.getResources(tagOne.getClass().getName());
//        System.out.println(enumeration.hasMoreElements());
//        while (enumeration.hasMoreElements()) {
//            URL url = enumeration.nextElement();
//            System.out.println(url);
//        }

        String temp1 = "C:\\Users\\zhouw\\Desktop\\temp\\temp1\\classes";
        String temp2 = "C:\\Users\\zhouw\\Desktop\\temp\\temp2\\classes";
        File tempFile1 = new File(temp1);
        System.out.println(tempFile1.exists());
        File tempFile2 = new File(temp2);
        System.out.println(tempFile1.exists());

        URLClassLoader urlClassLoader1 = new URLClassLoader(new URL[]{tempFile1.toURI().toURL()});
        Class<?> urlLoadTagClass1 = urlClassLoader1.loadClass("io.github.mikeiansky.java.base.jdk.clazz.load.LoadTag");
        Object tagFive = urlLoadTagClass1.getConstructor(new Class[]{String.class}).newInstance(tag);
        System.out.println("\ntagFive = " + tagFive);
        System.out.println(tagFive);
        System.out.println(tagFive.getClass().getName());
        System.out.println(tagFive.getClass().getClassLoader());

        URLClassLoader urlClassLoader2 = new URLClassLoader(new URL[]{tempFile2.toURI().toURL()});
        Class<?> urlLoadTagClass2 = urlClassLoader2.loadClass("io.github.mikeiansky.java.base.jdk.clazz.load.LoadTag");
        Object tagSix = urlLoadTagClass2.getConstructor(new Class[]{String.class}).newInstance(tag);
        System.out.println("\ntagSix = " + tagSix);
        System.out.println(tagSix);
        System.out.println(tagSix.getClass().getName());
        System.out.println(tagSix.getClass().getClassLoader());

        System.out.println();
        System.out.println("tagFive.equals(tagSix) = " + tagFive.equals(tagSix));

    }

}

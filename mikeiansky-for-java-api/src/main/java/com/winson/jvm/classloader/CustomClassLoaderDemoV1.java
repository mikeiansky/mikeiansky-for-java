package com.winson.jvm.classloader;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author winson
 * @date 2021/8/31
 **/
public class CustomClassLoaderDemoV1 {

    static {
        System.out.println("custom class loader initial ********* " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                if(name.startsWith("java") || name.endsWith("$1")){
//                    return super.loadClass(name);
//                }
//
//                System.out.println("loadClass name : " + name);
                String fileName1 = name.substring(name.lastIndexOf(".")+1)+".class";
////                System.out.println(fileName);
////                System.out.println("getClass()"+ getClass());
//                String filePath = "D:\\work\\java\\winson-for-java\\target\\classes\\com\\winson\\jvm\\classloader\\CustomClassLoaderDemoV1.class";
//
                InputStream in = getClass().getResourceAsStream(fileName1);
//                System.out.println(fileName1 + " , in : " + in);
//                InputStream in = null;
//                try {
//                    in = new FileInputStream(filePath);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//

                if (in == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] cb = new byte[in.available()];
                    in.read(cb);
                    return defineClass(name, cb, 0, cb.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                    try {
//                        String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";
//                        InputStream is = getClass().getResourceAsStream(fileName);
//                        if (is == null) {
//                            return super.loadClass(name);
//                        }
//                        System.out.println(fileName+ " , is : " + is);
//                        byte[] b = new byte[is.available()];
//                        is.read(b);
//                        return defineClass(name, b, 0, b.length);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }


                    return super.loadClass(name);
            }
        };

        Class clazz1 = classLoader.loadClass("com.winson.jvm.classloader.CustomClassLoaderDemoV1");
        Object obj1 = clazz1.newInstance();
        System.out.println(obj1.getClass());
        System.out.println(obj1 instanceof CustomClassLoaderDemoV1);
        System.out.println("obj1 classloader : " + obj1.getClass().getClassLoader());
        System.out.println("--------------");
        Class clazz2 = Class.forName("com.winson.jvm.classloader.CustomClassLoaderDemoV1");
        Object obj2 = clazz2.newInstance();
        System.out.println(obj2.getClass());
        System.out.println(obj2 instanceof CustomClassLoaderDemoV1);
        System.out.println("obj2 classloader : " + obj2.getClass().getClassLoader());

        CustomClassLoaderDemoV1 demoV1 = new CustomClassLoaderDemoV1();
        System.out.println(demoV1.getClass().getClassLoader().getResource(""));
        System.out.println(demoV1.getClass().getClassLoader().getResource("/"));
        System.out.println(demoV1.getClass().getClassLoader().getResource("."));
        System.out.println(demoV1.getClass().getResourceAsStream("CustomClassLoaderDemoV1.class").available());

        System.out.println("classLoader : " + classLoader);
//        Thread.currentThread().setContextClassLoader();
    }

}

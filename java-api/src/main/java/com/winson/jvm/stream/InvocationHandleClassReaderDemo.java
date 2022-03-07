package com.winson.jvm.stream;

import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * @author winson
 * @date 2022/3/7
 **/
public class InvocationHandleClassReaderDemo {

    public interface Hello {
        void sayHello(String msg);
    }

    public static class MyHello implements Hello {

        @Override
        public void sayHello(String msg) {
            System.out.println("myHello sayHello : " + msg);
        }

    }

    public static void main(String[] args) {
        final MyHello myHello = new MyHello();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Hello hello = (Hello) Proxy.newProxyInstance(classLoader, new Class[]{Hello.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("hello --> args : " + args[0]);
                myHello.sayHello((String)args[0]);
                return null;
            }
        });

        Class clazz = hello.getClass();
        String cn = clazz.getName();
        String name = cn.substring(cn.lastIndexOf(".")+1)+".class";
        System.out.println(name);
        InputStream in1 = clazz.getResourceAsStream(name);
        System.out.println(in1);
        InputStream in2 = clazz.getResourceAsStream("Proxy0.class");
        System.out.println(in2);

        hello.sayHello("winson");


    }

}

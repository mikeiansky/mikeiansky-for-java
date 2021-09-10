package com.winson.jvm.invoke;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author winson
 * @date 2021/9/10
 **/
public class JDKProxyDemoV1 {

    public interface WinsonService {

        String service(String msg);
        String service2(String msg);

    }

    public static class MyWinsonService implements WinsonService {

        @Override
        public String service(String msg) {
            String flag = "winson ==> " + msg;
            System.out.println("run on my winson service , " + flag);
            return flag;
        }

        @Override
        public String service2(String msg) {
            return null;
        }

    }

    public static void main(String[] args) {

        MyWinsonService winsonService = new MyWinsonService();
        winsonService.service("hello");
        System.out.println("real service is : " + winsonService);
        System.out.println("-----------> 1");

        Class clazz = winsonService.getClass();
        WinsonService proxyService = (WinsonService) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{WinsonService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before execute method : " + method.getName());
                int a = 1;
//                if(a>0){
//                    System.out.println("throw exception ");
//                    throw new IllegalArgumentException("test exception");
//                }
                return method.invoke(winsonService, args);
            }
        });
        System.out.println("proxy service is : " + proxyService.getClass());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = proxyService.service("proxy");
        System.out.println("proxy service result : " + result);

    }

}

package com.winson.jvm.metaspace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/5/16
 **/
public class MetaspaceSizeDemoV1 {

    static boolean end = false;

    private static MyInvocation myInvocation = new MyInvocation();
    private static MyInvocation myInvocation2 = new MyInvocation();
    private static ArrayList cache = new ArrayList();

    public static class MyServiceImpl implements MyService {

        @Override
        public void sayHello(String msg) {
            System.out.println("my service say : " + msg);
        }
    }

    public static class MyInvocation implements InvocationHandler {

        private MyServiceImpl myService = new MyServiceImpl();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("invocation ---------> ");
//            return method.invoke(myService,args);
            return null;
        }

    }

    /**
     * -XX:+UseSerialGC
     * 经过debug测试，是会缓存的起来的，不会无限的动态加载类
     */
    public static void main(String[] args) throws InterruptedException {
        MyService myService1 = (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{MyService.class}, myInvocation);

        MyService myService2 = (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{MyService.class}, myInvocation2);

        MyTarget myTarget = (MyTarget) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{MyTarget.class}, myInvocation);

        new Thread(() -> loadMetaData()).start();

        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
        System.out.println("metaspace size end ... ");
        end = true;
    }

    public interface MyService {
        void sayHello(String msg);
    }

    public interface MyTarget {
        void sayHello(String msg);
    }

    public static void loadMetaData() {
        while (!end) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            MyService myService = (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{MyService.class}, myInvocation);
            System.out.println("my serviec -----> " + myService.hashCode());
//            cache.add(myService);

        }
    }

}

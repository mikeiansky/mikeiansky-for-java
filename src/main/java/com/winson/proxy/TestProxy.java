package com.winson.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author winson
 * @date 2021/6/8
 **/
public class TestProxy {

    public interface Person {
        void sayGood(String msg);
    }

    public static class User implements Person {

        @Override
        public void sayGood(String msg) {
            System.out.println("user say good msg : " + msg);
        }

    }

    public void sayHello(String msg) {
        System.out.println("proxy say hello : " + msg);
    }

    public static void main(String[] args) {
        System.out.println("test proxy start ... ");

        TestProxy testProxy = new TestProxy();
        CGlibProxy proxy = new CGlibProxy(testProxy);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testProxy.getClass());
        enhancer.setCallback(proxy);
        System.out.println("1");
        TestProxy target = (TestProxy) enhancer.create();
        System.out.println("testProxy--->" + testProxy);
        System.out.println("target--->" + target);
        System.out.println("target.getClass()--->" + target.getClass());
        System.out.println("ready invoke method ... ");
        target.sayHello("winson");
        System.out.println("---------- next test ---------- ");

        User user = new User();
        CGlibProxy proxy2 = new CGlibProxy(user);
        Enhancer e2 = new Enhancer();
        e2.setSuperclass(Person.class);
        e2.setCallback(proxy2);
        Person person = (Person) e2.create();
        System.out.println("person : " + person);
        System.out.println("person.getClass() : " + person.getClass());
        person.sayGood("haha");

        System.out.println("test proxy stop ... ");
    }

}

package com.winson.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author winson
 * @date 2021/6/8
 **/
public class TestProxy {

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
        System.out.println("testProxy--->"+testProxy);
        System.out.println("target--->"+target);
        System.out.println("ready invoke method ... ");
        target.sayHello("winson");

        System.out.println("test proxy stop ... ");
    }

}

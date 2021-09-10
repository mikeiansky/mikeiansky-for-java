package com.winson.jvm.invoke;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/9/10
 **/
public class CGLIBDemoV1 {

    public String service(String msg) {
        String result = "[ - "+ msg + " - ]";
        System.out.println("this is : " + this);
        System.out.println("real cglib enhance service : " + result);
        return result;
    }

    public static void main(String[] args) {

        System.out.println("ready ... ");

        try {
            System.in.read();
//            System.out.println("read 111 ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLIBDemoV1.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before run ... methodProxy : " + methodProxy);
//                System.out.println("cglib intercept object : " + o);
                return methodProxy.invokeSuper(o, objects);
            }
        });

        CGLIBDemoV1 service = (CGLIBDemoV1) enhancer.create();
        String result = service.service("ciwei");
        System.out.println("service result on main : " + result);

        try {
//            Thread.sleep(1000);
//            System.in.read(new byte[10]);
            System.in.read();
            System.in.read();
//            System.out.println("read 222 ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end ... ");
    }

}

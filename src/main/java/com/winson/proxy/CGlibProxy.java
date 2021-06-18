package com.winson.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class CGlibProxy implements MethodInterceptor {

    private Object target;

    public CGlibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib intercept method start ... ");
        Object result = methodProxy.invoke(target, objects);
        System.out.println("proxy intercept result : " + result);
        System.out.println("cglib intercept method end ... ");
        return result;
    }

}

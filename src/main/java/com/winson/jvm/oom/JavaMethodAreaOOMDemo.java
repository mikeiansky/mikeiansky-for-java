package com.winson.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class JavaMethodAreaOOMDemo {

    // -XX:PermSize=10M -XX:MaxPermSize=10M
    public static void main(String[] args) {

        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HeapOOMDemo.OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }

    }

}

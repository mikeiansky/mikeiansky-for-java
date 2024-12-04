package com.winson.jvm.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author winson
 * @date 2021/10/9
 **/
public class CGLIBDemoV2 {

    public static class Animal {

        public String eat(String food) {
            System.out.println("animal eat : " + food);
            return food + " is delicious ";
        }

    }

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Animal.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("source.getClass() : " + source.getClass());
                System.out.println("method.getName() : " + method.getName());
                System.out.println("objects : " + Arrays.asList(objects));
                System.out.println("methodProxy.getSuperName() : " + methodProxy.getSuperName());
                System.out.println("methodProxy.getSuperIndex() : " + methodProxy.getSuperIndex());
                System.out.println("methodProxy.getSignature() : " + methodProxy.getSignature());
                method.getParameterTypes();
                method.getParameters();
                return methodProxy.invokeSuper(source, objects);
            }
        });

        Animal animal = (Animal) enhancer.create();
        animal.eat("grass");

    }

}

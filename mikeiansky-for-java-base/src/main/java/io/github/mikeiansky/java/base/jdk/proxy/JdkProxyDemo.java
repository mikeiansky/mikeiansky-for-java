package io.github.mikeiansky.java.base.jdk.proxy;

import java.lang.reflect.*;

/**
 * @author mike ian
 * @date 2024/12/12
 * @desc
 **/
public class JdkProxyDemo {

    public interface OneFace {
        void hello(String message);
    }

    public static class One implements OneFace {
        private One() {
            System.out.println("create one");
        }

        @Override
        public void hello(String message) {
            System.out.println("one hello " + message);
        }
    }

    public static class OneProxy implements InvocationHandler {

        private Object target;

        public OneProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("invoke ::: target : " + target + ", method : " + method + " , args : " + args);
            return method.invoke(target, args);
        }

    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = One.class.getDeclaredConstructor();
        System.out.println(constructor);

        constructor.setAccessible(true);
        Object oneObject = constructor.newInstance();
        System.out.println("oneObject :: " + oneObject);
        System.out.println("========= proxy =========");

        Object oneProxy = Proxy.newProxyInstance(JdkProxyDemo.class.getClassLoader(),
                One.class.getClass().getInterfaces(),
                new OneProxy(oneObject));
        System.out.println("oneProxy :: " + oneProxy);
        System.out.println("oneProxy instanceof One :: " + (oneProxy instanceof OneFace));

    }

}

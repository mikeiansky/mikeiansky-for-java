package io.github.mikeiansky.java.base.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author mike ian
 * @date 2024/12/12
 * @desc
 **/
public class ProxyConstructSelfDemo {

    public interface IOne {
    }

    public static class One implements IOne {

        private IOne parent;

        public One(IOne parent) {
            this.parent = parent;
        }

    }

    public static class OneInvokeHandler implements InvocationHandler {

        private Object target;

        public void setTarget(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (target == null) {
                return null;
            }
            System.out.println("do really invoke , method : " + method);
            return method.invoke(target, args);
        }

    }

    public static void main(String[] args) {
        System.out.println(One.class.getInterfaces());

        OneInvokeHandler oneInvokeHandler = new OneInvokeHandler();
        IOne proxy = (IOne) Proxy.newProxyInstance(ProxyConstructSelfDemo.class.getClassLoader(),
                One.class.getInterfaces(),
                oneInvokeHandler);
        System.out.println(proxy);
        One one = new One(proxy);
        oneInvokeHandler.setTarget(one);
        System.out.println("one : " + one);
        System.out.println("one.parent : " + one.parent);


    }

}

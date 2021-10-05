package com.winson.jdkapi.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/5
 **/
public class GenericDemoV2 {

    public static class MyGenericNormal{

    }

    public static class MySubGeneric extends MyGenericNormal{

    }

    public static class WithGeneric<T>{

        public T t;

        public WithGeneric(T t) {
            this.t = t;
        }

        public T getTarget(){
            System.out.println(t);
            return t;
        }
    }

    public static class WithGenericImpl {

        public void doWithGenericType(WithGeneric<?> wg, Object target){
            System.out.println(wg.getTarget());
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(MyGenericNormal.class.getGenericSuperclass().getClass());
        System.out.println(MyGenericNormal.class.getGenericSuperclass().getTypeName());

        System.out.println(WithGeneric.class.getGenericSuperclass());
        System.out.println(WithGeneric.class.getGenericSuperclass().getTypeName());
        System.out.println(WithGeneric.class.isAssignableFrom(ParameterizedType.class));
        System.out.println(WithGeneric.class.isAssignableFrom(TypeVariable.class));
        Package pk = WithGeneric.class.getPackage();
        System.out.println(pk.getName());
        System.out.println(pk.getClass());

        System.out.println(Arrays.stream(WithGeneric.class.getGenericInterfaces()).collect(Collectors.toList()));
        System.out.println(WithGeneric.class.getTypeName());
        System.out.println(WithGeneric.class.getComponentType());

        // do some thing with ? ， 通配符测试
        WithGenericImpl withGeneric = new WithGenericImpl();
        WithGeneric wg = new WithGeneric(new Object());
        withGeneric.doWithGenericType(wg, null);
        WithGeneric<MySubGeneric> wm = new WithGeneric<>(new MySubGeneric());
        withGeneric.doWithGenericType(wm, null);

    }

}

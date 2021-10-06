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
        public void doNormal(){
            System.out.println("generic normal do something !");
        }
    }

    public static class MySubGeneric extends MyGenericNormal{

    }

    // 这里的T在字节码文件里面会被转成Object
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

    // 这里的T在字节码里面会被转成MyGenericNormal
    public static class GenericWithExtend<T extends MyGenericNormal>{
        public void test(T normal){
            normal.doNormal();
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

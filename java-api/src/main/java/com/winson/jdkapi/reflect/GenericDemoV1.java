package com.winson.jdkapi.reflect;


import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/4
 **/
public class GenericDemoV1 {

    public static class Flag {
    }

    public static class FlagWithType<T> {

    }

    public static class FlagWithTwoType<S, R, B> {

    }

    public static class FlagTwo {

    }

    public static class FlagThree {

    }

    public static class FlagFour {

    }

    public static class FlagFive {

    }

    interface InterfaceNormal {

    }

    interface InterfaceOne {
    }

    interface InterfaceTwo extends InterfaceOne, InterfaceNormal {
    }

    interface InterfaceThree {
    }

    interface InterfaceFour {
    }

    interface InterfaceFive {
    }

    public static class SuperUser<E, S, R> implements InterfaceTwo, InterfaceThree {

        public E getFlag(int a, E e, E[] es) {
            System.out.println("getFlag invoke by : " + this + " , e : " + e + " , es : " + es);
            return null;
        }

        public void test(int p) {

        }

        public void testSuper(List<? super E> ee) {

        }

        public E getOther(int a, E... es) {
            System.out.println("getOther invoke by : " + this + " , es : " + es);
            return null;
        }

        public E getWithType(E e, FlagWithType<InterfaceOne> flagWithType, FlagWithTwoType<InterfaceTwo, InterfaceTwo, E> fe) {
            return null;
        }

    }

    public static class NormalUser<T extends InterfaceFive, S, B> extends SuperUser<Flag, FlagWithType<InterfaceNormal>, FlagWithTwoType<InterfaceOne, S, ? super T>> implements InterfaceFour, InterfaceFive {

        public Flag getFlag(int a, Flag e, Flag[] es) {
            System.out.println("getFlag on normal invoke by : " + this + " , e : " + e + " , es : " + es);
            return null;
        }

//        public Flag getOther(int a, Flag... es) {
//            System.out.println("getOther on normal invoke by : " + this + " , es : " + es);
//            return null;
//        }

    }

    public static void main(String[] args) throws NoSuchMethodException {

        Class clazz = NormalUser.class;
        displayClassInfo(clazz);

//        System.out.println("========= flag method ========");
//        Method flagMethod = clazz.getMethod("getFlag", int.class, Object.class, Object[].class);
//        displayMethod(flagMethod);

//        System.out.println("========= other method =========");
//        Method otherMethod = clazz.getMethod("getOther", int.class, Object[].class);
//        displayMethod(otherMethod);

//        System.out.println("======= test invoke ========");
//        Flag flag = new Flag();
//        NormalUser normalUser = new NormalUser();
//        normalUser.getOther(1, flag);
//
//        SuperUser superUser = new SuperUser();
//        superUser.getOther(1, flag);
//
//        Object obj = new Object();
//        try {
//            otherMethod.invoke(normalUser, 1, new Object[]{obj});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            otherMethod.invoke(superUser, 1, new Object[]{obj});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static void displayClassInfo(Class clazz) {
        System.out.println("========= class info =========");
        System.out.println("super class : " + clazz.getSuperclass());
        System.out.println("genericSuperClass : " + clazz.getGenericSuperclass());
        System.out.println("genericSuperClass.getTypeName() : " + clazz.getGenericSuperclass().getTypeName());
        System.out.println("genericSuperClass.getClass() : " + clazz.getGenericSuperclass().getClass());
        System.out.println("========= class parameterize info");
        ParameterizedType parameterizedType = ParameterizedType.class.cast(clazz.getGenericSuperclass());
        System.out.println("parameterizedType : " + parameterizedType);
        System.out.println("parameterizedType.getRawType() : " + parameterizedType.getRawType());
        System.out.println("parameterizedType.getTypeName() : " + parameterizedType.getTypeName());
        System.out.println("parameterizedType.getOwnerType() : " + parameterizedType.getOwnerType());
        Type[] types = parameterizedType.getActualTypeArguments();
        System.out.println("parameterizedType.getActualTypeArguments().length : " + types.length);
        System.out.println("========== class parameterize type info");
        for (Type type : types) {
            System.out.println("--------- type  : " + type);
            System.out.println("type.getTypeName() : " + type.getTypeName());
            System.out.println("type.getClass() : " + type.getClass());
            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                ParameterizedType subType = ParameterizedType.class.cast(type);
                System.out.println("---------------- subType : " + subType);
                System.out.println("subType.getTypeName() : " + subType.getTypeName());
                System.out.println("subType.getRawType() : " + subType.getRawType());
                System.out.println("subType.getOwnerType() : " + subType.getOwnerType());
                Type[] subParameterizeTypes = subType.getActualTypeArguments();
                System.out.println("subType.getActualTypeArguments().length : " + subParameterizeTypes.length);
                for (Type spt : subParameterizeTypes) {
                    System.out.println("------------------- spt : " + spt);
                    System.out.println("spt.getTypeName() : " + spt.getTypeName());
                    System.out.println("spt.getClass() : " + spt.getClass());
                    if (TypeVariable.class.isAssignableFrom(spt.getClass())) {
                        TypeVariable tv = TypeVariable.class.cast(spt);
                        System.out.println("----------------------------- tv : " + tv);
                        System.out.println("tv.getName() : " + tv.getName());
                        System.out.println("tv.getTypeName() : " + tv.getTypeName());
                        System.out.println("tv.getClass() : " + tv.getClass());
                        System.out.println("tv.getBounds() : " + Arrays.stream(tv.getBounds()).collect(Collectors.toList()));
                        System.out.println("tv.getGenericDeclaration() : " + tv.getGenericDeclaration());
                    } else if (WildcardType.class.isAssignableFrom(spt.getClass())) {
                        WildcardType wt = WildcardType.class.cast(spt);
                        System.out.println("------------------------------ wt : " + wt);
                        System.out.println("wt.getLowerBounds() : " + Arrays.stream(wt.getLowerBounds()).collect(Collectors.toList()));
                        System.out.println("wt.getUpperBounds() : " + Arrays.stream(wt.getUpperBounds()).collect(Collectors.toList()));
                    }
                }
            }
        }
        System.out.println("========= class interface info ==== ");
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface + " , extend interface size : " + anInterface.getInterfaces().length);
        }
        System.out.println("=========== super class interface info ======== ");
        Class[] sis = clazz.getSuperclass().getInterfaces();
        for (Class si : sis) {
            System.out.println(si + " , extend interface size : " + si.getInterfaces().length);
        }
    }

    public static void displayMethod(Method method) {
        System.out.println("return type : " + method.getReturnType());
        System.out.println("declaring class : " + method.getDeclaringClass());
        for (Parameter parameter : method.getParameters()) {
            System.out.println("parameter name : " + parameter.getName());
            System.out.println("parameter type : " + parameter.getType());
            System.out.println("parameter parameterizedType : " + parameter.getParameterizedType());
        }
    }

}

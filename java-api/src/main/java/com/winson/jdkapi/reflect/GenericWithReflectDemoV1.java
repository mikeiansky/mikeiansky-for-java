package com.winson.jdkapi.reflect;


import com.winson.jdkapi.reflect.ReflectAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/4
 **/
public class GenericWithReflectDemoV1 {

    public static class Flag {
    }

    public static class FlagWithType<T> {

    }

    public static class FlagWithTheeType<S, R, B> {

    }

    public static class FlagWithTwoType<F, T> {

    }

    public static class FlagThree {

    }

    public static class FlagFour {

    }

    public static class FlagFive {

    }

    interface InterfaceNormal<N> {

    }

    interface InterfaceOne<O, P> {
    }

    interface InterfaceTwo<I, J, K> extends InterfaceOne<Flag, InterfaceNormal<? super K>>, InterfaceNormal<FlagWithTheeType<I, ? extends J, K>> {
    }

    interface InterfaceThree<R> {
    }

    interface InterfaceFour {
    }

    interface InterfaceFive {
    }

    // 注解信息会写入到字节码里面
    @ReflectAnnotation("SuperUser-class")
    public static class SuperUser<A, E, S, R, F, G> implements InterfaceTwo<FlagWithType<E>, FlagWithTwoType<? extends S, ? super E>, R>, InterfaceThree<E> {

        @ReflectAnnotation("super-user-reflect-method-getFlag")
        public E getFlag(@ReflectAnnotation("p-a-int") int a, @ReflectAnnotation("p-e-int") E e, @ReflectAnnotation("p-es-E[]") E[] es) {
            System.out.println("getFlag invoke by : " + this + " , e : " + e + " , es : " + es);
            return null;
        }

        @ReflectAnnotation("super-user-reflect-method-getFlagWithAnnotation")
        public E getFlagWithAnnotation(@ReflectAnnotation("p-a-int") int a, @ReflectAnnotation("p-e-E") E e, @ReflectAnnotation("p-fe-FlagWithType") FlagWithType<E> fe, @ReflectAnnotation("p-fte-FlagWithTheeType") FlagWithTheeType<Flag, ? extends E, ? super A> fte, @ReflectAnnotation("p-fs-F[]") F[] fs) {
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

        public E getWithType(E e, FlagWithType<InterfaceOne> flagWithType, FlagWithTheeType<InterfaceTwo, InterfaceTwo, E> fe) {
            return null;
        }

    }

    public static class NormalUser<T extends InterfaceFive, S, B> extends SuperUser<S, Flag, FlagWithType<InterfaceNormal<T>>, FlagWithTheeType<InterfaceOne<S, B>, ? extends S, ? super T>, FlagWithType<? extends T>, FlagWithType<?>> implements InterfaceFour, InterfaceFive {

        public Flag getFlag(int a, Flag e, Flag[] es) {
            System.out.println("getFlag on normal invoke by : " + this + " , e : " + e + " , es : " + es);
            return null;
        }

        @Override
        public Flag getFlagWithAnnotation(int a, Flag flag, FlagWithType<Flag> fe, FlagWithTheeType<Flag, ? extends Flag, ? super S> fte, FlagWithType<? extends T>[] flagWithTypes) {
            return super.getFlagWithAnnotation(a, flag, fe, fte, flagWithTypes);
        }

        //        public Flag getOther(int a, Flag... es) {
//            System.out.println("getOther on normal invoke by : " + this + " , es : " + es);
//            return null;
//        }

    }

    public static void main(String[] args) throws NoSuchMethodException {

        Class clazz = NormalUser.class;
        displayClassInfo(clazz);

        System.out.println("========= flag method ========");
        Method flagMethod = clazz.getMethod("getFlag", int.class, Object.class, Object[].class);
        displayMethod(flagMethod);

        Method flagMethodWithAnnotation = clazz.getMethod("getFlagWithAnnotation", int.class, Object.class, FlagWithType.class, FlagWithTheeType.class, Object[].class);
        displayMethod(flagMethodWithAnnotation);

        System.out.println("========= other method =========");
        Method otherMethod = clazz.getMethod("getOther", int.class, Object[].class);
        displayMethod(otherMethod);

        System.out.println("========= getMethods =========");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            displayMethod(method);
        }

        System.out.println("========= getDeclaredMethods =========");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            displayMethod(declaredMethod);
        }

        System.out.println("======= test invoke ========");
        Flag flag = new Flag();
        NormalUser normalUser = new NormalUser();
        normalUser.getOther(1, flag);

        SuperUser superUser = new SuperUser();
        superUser.getOther(1, flag);

        Object obj = new Object();
        try {
            otherMethod.invoke(normalUser, 1, new Object[]{obj});
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            otherMethod.invoke(superUser, 1, new Object[]{obj});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void displayClassInfo(Class clazz) {
        System.out.println("========= class info =========");
        System.out.println(clazz.getCanonicalName());
        System.out.println("clazz.getSuperclass() : " + clazz.getSuperclass());
        System.out.println("clazz.getAnnotatedSuperclass() : " + clazz.getAnnotatedSuperclass());
        System.out.println("clazz.getAnnotatedInterfaces() : " + Arrays.stream(clazz.getAnnotatedInterfaces()).collect(Collectors.toList()));
        System.out.println("clazz.getAnnotations() : " + Arrays.stream(clazz.getAnnotations()).collect(Collectors.toList()));
        System.out.println("clazz.getDeclaredAnnotations() : " + Arrays.stream(clazz.getDeclaredAnnotations()).collect(Collectors.toList()));
        System.out.println("clazz.getGenericInterfaces() : " + Arrays.stream(clazz.getGenericInterfaces()).collect(Collectors.toList()));
        System.out.println("clazz.getGenericSuperclass() : " + clazz.getGenericSuperclass());
        System.out.println("clazz.getGenericSuperclass().getTypeName() : " + clazz.getGenericSuperclass().getTypeName());
        System.out.println("clazz.getGenericSuperclass().getClass() : " + clazz.getGenericSuperclass().getClass());
        for (Annotation annotation : clazz.getSuperclass().getAnnotations()) {
            if (annotation.annotationType().isAssignableFrom(ReflectAnnotation.class)) {
                ReflectAnnotation ra = (ReflectAnnotation) annotation;
                System.out.println("clazz.getSuperclass().getAnnotation(ReflectAnnotation.class).getValue() : " + ra.value());
            }
        }
        System.out.println("========= class parameterize info");
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        System.out.println("parameterizedType : " + parameterizedType);
        System.out.println("parameterizedType.getRawType() : " + parameterizedType.getRawType());
        System.out.println("parameterizedType.getTypeName() : " + parameterizedType.getTypeName());
        System.out.println("parameterizedType.getOwnerType() : " + parameterizedType.getOwnerType());
        Type[] types = parameterizedType.getActualTypeArguments();
        System.out.println("parameterizedType.getActualTypeArguments().length : " + types.length);
        System.out.println("========== class parameterize type info");
        for (Type type : types) {
//            System.out.println("xxxxxxxxxxx type : " + type.getClass());
            if (TypeVariable.class.isAssignableFrom(type.getClass())) {
                TypeVariable tv = (TypeVariable) type;
                System.out.println("--------- rawTypeVariable  : " + tv);
                System.out.println("tv.getBounds() : " + Arrays.stream(tv.getBounds()).collect(Collectors.toList()));
                System.out.println("tv.getName() : " + tv.getName());
                System.out.println("tv.getTypeName() : " + tv.getTypeName());
                System.out.println("tv.getAnnotatedBounds() : " + Arrays.stream(tv.getAnnotatedBounds()).collect(Collectors.toList()));
                System.out.println("tv.getAnnotations() : " + Arrays.stream(tv.getAnnotations()).collect(Collectors.toList()));
            } else if (Class.class.isAssignableFrom(type.getClass())) {
                System.out.println("--------- rawType  : " + type);
                System.out.println("type.getTypeName() : " + type.getTypeName());
                System.out.println("type.getClass() : " + type.getClass());
            } else if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                ParameterizedType topType = (ParameterizedType) type;
                System.out.println("--------- topType : " + topType);
                System.out.println("topType.getTypeName() : " + topType.getTypeName());
                System.out.println("topType.getRawType() : " + topType.getRawType());
                System.out.println("topType.getOwnerType() : " + topType.getOwnerType());
                Type[] subParameterizeTypes = topType.getActualTypeArguments();
                System.out.println("topType.getActualTypeArguments().length : " + subParameterizeTypes.length);
                for (Type subType : subParameterizeTypes) {
                    System.out.println("------------------ subType : " + subType);
                    System.out.println("subType.getTypeName() : " + subType.getTypeName());
                    System.out.println("subType.getClass() : " + subType.getClass());
                    if (TypeVariable.class.isAssignableFrom(subType.getClass())) {
                        TypeVariable tv = (TypeVariable) subType;
//                        System.out.println("------------------------------------ tv : " + tv);
                        System.out.println("subType is [ TypeVariable ]");
                        System.out.println("subType.getName() : " + tv.getName());
                        System.out.println("subType.getBounds() : " + Arrays.stream(tv.getBounds()).collect(Collectors.toList()));
                        System.out.println("subType.getGenericDeclaration() : " + tv.getGenericDeclaration());
                    } else if (WildcardType.class.isAssignableFrom(subType.getClass())) {
                        WildcardType wt = (WildcardType) subType;
                        System.out.println("subType is [ WildcardType ]");
                        System.out.println("subType.getLowerBounds() : " + Arrays.stream(wt.getLowerBounds()).collect(Collectors.toList()));
                        System.out.println("subType.getUpperBounds() : " + Arrays.stream(wt.getUpperBounds()).collect(Collectors.toList()));
                    } else if (ParameterizedType.class.isAssignableFrom(subType.getClass())) {
                        ParameterizedType spt = (ParameterizedType) subType;
                        System.out.println("subType is [ ParameterizedType ]");
                        System.out.println("subType.getRawType() : " + spt.getRawType());
                        System.out.println("subType.getOwnerType() : " + spt.getOwnerType());
                        System.out.println("subType.getActualTypeArguments() : " + Arrays.stream(spt.getActualTypeArguments()).collect(Collectors.toList()));
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
        System.out.println("========================================== method : " + method);
        System.out.println("method.getName() : " + method.getName());
        System.out.println("method.getReturnType() : " + method.getReturnType());
        System.out.println("method.getDeclaringClass() : " + method.getDeclaringClass());
        System.out.println("method.getAnnotations() : " + Arrays.stream(method.getAnnotations()).collect(Collectors.toList()));
        System.out.println("------------ parameter info");
        for (Parameter parameter : method.getParameters()) {
            System.out.println("------------------ parameter : " + parameter);
            System.out.println("parameter.getName() : " + parameter.getName());
            System.out.println("parameter.getType() : " + parameter.getType());
            System.out.println("parameter.getAnnotations() : " + Arrays.stream(parameter.getAnnotations()).collect(Collectors.toList()));
            System.out.println("parameter.getClass() : " + parameter.getClass());
            Type pt = parameter.getParameterizedType();
            System.out.println("parameter.getParameterizedType() : " + pt);
            Class ptClazz = pt.getClass();
            System.out.println("parameter.getParameterizedType().getClass() : " + ptClazz);
            if (Class.class.isAssignableFrom(ptClazz)) {
                Class pz = (Class) pt;
                System.out.println("------------------------ pz : " + pz);
                System.out.println("pz.getName() : " + pz.getName());
                System.out.println("pz.getTypeName() : " + pz.getTypeName());
            } else if (TypeVariable.class.isAssignableFrom(ptClazz)) {
                TypeVariable tv = (TypeVariable) pt;
                System.out.println("------------------------ tv : " + tv);
                System.out.println("tv.getTypeName() : " + tv.getTypeName());
                System.out.println("tv.getBounds() : " + Arrays.stream(tv.getBounds()).collect(Collectors.toList()));
            } else if (ParameterizedType.class.isAssignableFrom(ptClazz)) {
                ParameterizedType ppt = (ParameterizedType) pt;
                System.out.println("------------------------ ppt : " + ppt);
                System.out.println("ppt.getRawType() : " + ppt.getRawType());
                System.out.println("ppt.getOwnerType() : " + ppt.getOwnerType());
                System.out.println("ppt.getTypeName() : " + ppt.getTypeName());
                System.out.println("ppt.getClass() : " + ppt.getClass());
                List<Type> typeList = Arrays.stream(ppt.getActualTypeArguments()).collect(Collectors.toList());
                System.out.println("ppt.getActualTypeArguments() : " + typeList);
                for (Type type : typeList) {
                    if (WildcardType.class.isAssignableFrom(type.getClass())) {
                        WildcardType wt = (WildcardType) type;
                        System.out.println("------------------------------------ wt : " + wt);
                        System.out.println("wt.getTypeName() : " + wt.getTypeName());
                        System.out.println("wt.getClass() : " + wt.getClass());
                        System.out.println("wt.getLowerBounds() : " + Arrays.stream(wt.getLowerBounds()).collect(Collectors.toList()));
                        System.out.println("wt.getUpperBounds() : " + Arrays.stream(wt.getUpperBounds()).collect(Collectors.toList()));
                    } else if (Class.class.isAssignableFrom(type.getClass())) {
                        Class ct = (Class) type;
                        System.out.println("------------------------------------ ct : " + ct);
                        System.out.println("ct.getName() : " + ct.getName());
                        System.out.println("ct.getTypeName() : " + ct.getTypeName());
                    }
                }
            } else if (GenericArrayType.class.isAssignableFrom(ptClazz)) {
                GenericArrayType gt = (GenericArrayType) pt;
                System.out.println("------------------------ gt : " + gt);
                System.out.println("gt.getTypeName() : " + gt.getTypeName());
                System.out.println("gt.getGenericComponentType() : " + gt.getGenericComponentType());
                System.out.println("gt.getClass() : " + gt.getClass());
            }
        }
    }

}

package com.winson.jdkapi.reflect.v1;


import com.winson.utils.common.PrintUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    interface InterfaceTwo<I, J, K> extends InterfaceOne<Flag, InterfaceNormal<? super K>>,
            InterfaceNormal<FlagWithTheeType<I, ? extends J, K>> {
    }

    interface InterfaceThree<R> {
    }

    interface InterfaceFour {
    }

    interface InterfaceFive<T> {
    }

    interface InterfaceSix<T> {

    }

    interface InterfaceSeven<T> extends InterfaceSix<T> {

    }

    // 注解信息会写入到字节码里面

    @ReflectAnnotation("SuperUser-class")
    public static class SuperUser<A, E, S, R, F, G, H> implements InterfaceTwo<FlagWithType<E>,
            FlagWithTwoType<? extends S, ? super E>, R>, InterfaceThree<E> {

        @ReflectAnnotation("super-user-reflect-method-getFlag")
        public E getFlag(@ReflectAnnotation("p-a-int") int a,
                         @ReflectAnnotation("p-e-int") E e,
                         @ReflectAnnotation("p-es-E[]") E[] es) {
            System.out.println("getFlag invoke by : " + this + " , e : " + e + " , es : " + es);
            return null;
        }

        @ReflectAnnotation("super-user-reflect-method-getFlagWithAnnotation")
        public E getFlagWithAnnotation(@ReflectAnnotation("p-a-int") int a,
                                       @ReflectAnnotation("p-e-E") E e,
                                       @ReflectAnnotation("p-fe-FlagWithType") FlagWithType<E> fe,
                                       @ReflectAnnotation("p-fte-FlagWithTheeType") FlagWithTheeType<Flag, ? extends E, ? super A> fte,
                                       @ReflectAnnotation("p-fs-F[]") F[] fs) {
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

        public E getWithType(E e, FlagWithType<InterfaceOne> flagWithType,
                             FlagWithTheeType<InterfaceTwo, InterfaceTwo, E> fe) {
            return null;
        }

    }

    public static class MyInterface implements InterfaceThree<Flag>, InterfaceFour, InterfaceFive {

    }

    @NormalAnnotation
    public static class NormalUser<T extends InterfaceFive, S, B>
            extends SuperUser<@NormalAnnotation S, Flag, FlagWithType<InterfaceNormal<@NormalAnnotation T>>,
            FlagWithTheeType<InterfaceOne<@NormalAnnotation S, FlagFour>, ? extends S, ? super T>,
            FlagWithType<? extends T>, FlagWithType<?>, InterfaceNormal<S>>
            implements InterfaceFour, InterfaceFive<B>, InterfaceSeven<FlagWithType<InterfaceNormal<T>>> {

        public Flag getFlag(int a, Flag e, Flag[] es) {
            System.out.println("getFlag on normal invoke by : " + this + " , e : " + e + " , es : " + es);
            return null;
        }

        @Override
        public Flag getFlagWithAnnotation(int a, Flag flag, FlagWithType<Flag> fe,
                                          FlagWithTheeType<Flag, ? extends Flag, ? super S> fte,
                                          FlagWithType<? extends T>[] flagWithTypes) {
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

        Method flagMethodWithAnnotation = clazz.getMethod("getFlagWithAnnotation", int.class, Object.class,
                FlagWithType.class, FlagWithTheeType.class, Object[].class);
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

    public static void displayGetAnnotatedSuperclass(Class clazz) {
        System.out.println("start ----> clazz.getAnnotatedSuperclass() : " + clazz.getAnnotatedSuperclass());
        AnnotatedType annotatedType = clazz.getAnnotatedSuperclass();
        if (annotatedType == null) {
            return;
        }
        Type type = annotatedType.getType();
        System.out.println("    getType():" + annotatedType.getType());
        System.out.println("    getAnnotations():" + Stream.of(annotatedType.getAnnotations()).collect(Collectors.toList()));
        System.out.println("    getDeclaredAnnotations():" + Stream.of(annotatedType.getDeclaredAnnotations()).collect(Collectors.toList()));
        System.out.println("    type.getTypeName():" + type.getTypeName());
        System.out.println("    type.getClass():" + type.getClass());
        System.out.println("end ----> clazz.getAnnotatedSuperclass() : " + clazz.getAnnotatedSuperclass());
    }

    public static void displayGetAnnotatedInterfaces(Class clazz) {
        System.out.println("start ----> clazz.getAnnotatedInterfaces() : " + clazz.getAnnotatedInterfaces());
        AnnotatedType[] annotatedTypes = clazz.getAnnotatedInterfaces();
        for (AnnotatedType annotatedType : annotatedTypes) {
            Type type = annotatedType.getType();
            System.out.println("    annotatedType.getType():" + annotatedType.getType());
            System.out.println("    annotatedType.getAnnotations():" + Stream.of(annotatedType.getAnnotations()).collect(Collectors.toList()));
            System.out.println("    annotatedType.getDeclaredAnnotations():" + Stream.of(annotatedType.getDeclaredAnnotations()).collect(Collectors.toList()));
            System.out.println("    annotatedType.getType().getTypeName():" + type.getTypeName());
            System.out.println("    annotatedType.getType().getClass():" + type.getClass());
        }
        System.out.println("end ----> clazz.getAnnotatedInterfaces() : " + clazz.getAnnotatedInterfaces());
    }

    private static void displayClassInfo(Class clazz) {
        System.out.println("========= class info =========");
        System.out.println("clazz : " + clazz);
        System.out.println("clazz.getCanonicalName() : " + clazz.getCanonicalName());
        System.out.println("clazz.getConstructors() : " + clazz.getConstructors());
        System.out.println("clazz.getDeclaredConstructors() : " + clazz.getDeclaredConstructors());
        System.out.println("clazz.getTypeParameters() : " +
                Arrays.stream(Optional.ofNullable(clazz.getTypeParameters()).orElse(new TypeVariable[]{}))
                        .map(TypeVariable::getName).collect(Collectors.toList()));

        System.out.println("clazz.getSuperclass() : " + clazz.getSuperclass());
        if (clazz.getSuperclass() != null) {
            System.out.println("clazz.getSuperclass().getTypeParameters() : " +
                    Arrays.stream(Optional.ofNullable(clazz.getSuperclass().getTypeParameters()).orElse(new TypeVariable[]{}))
                            .map(TypeVariable::getName).collect(Collectors.toList()));
        }
        System.out.println("clazz.getInterfaces() : " + Stream.of(clazz.getInterfaces()).collect(Collectors.toList()));
        System.out.println("clazz.getAnnotations() : " + Arrays.stream(clazz.getAnnotations()).collect(Collectors.toList()));
        System.out.println("clazz.getDeclaredAnnotations() : " + Arrays.stream(clazz.getDeclaredAnnotations()).collect(Collectors.toList()));
        System.out.println("clazz.getGenericSuperclass() : " + clazz.getGenericSuperclass());
        System.out.println("clazz.getGenericInterfaces() : " + Arrays.stream(clazz.getGenericInterfaces()).collect(Collectors.toList()));
//        System.out.println("clazz.getAnnotatedSuperclass() : " + clazz.getAnnotatedSuperclass());
        displayGetAnnotatedSuperclass(clazz);
//        System.out.println("clazz.getAnnotatedInterfaces() : " + Arrays.stream(clazz.getAnnotatedInterfaces()).collect(Collectors.toList()));
        displayGetAnnotatedInterfaces(clazz);
        if (clazz.getGenericSuperclass() != null) {
            System.out.println("clazz.getGenericSuperclass().getTypeName() : " + clazz.getGenericSuperclass().getTypeName());
        }
        if (clazz.getGenericSuperclass() != null) {
            System.out.println("clazz.getGenericSuperclass().getClass() : " + clazz.getGenericSuperclass().getClass());
        }
        if (clazz.getSuperclass() != null && clazz.getSuperclass().getAnnotations() != null) {
            for (Annotation annotation : clazz.getSuperclass().getAnnotations()) {
                if (annotation.annotationType().isAssignableFrom(ReflectAnnotation.class)) {
                    ReflectAnnotation ra = (ReflectAnnotation) annotation;
                    System.out.println("clazz.getSuperclass().getAnnotation(ReflectAnnotation.class).getValue() : " + ra.value());
                }
            }
        }

        System.out.println("========= class parameterize info start");
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        System.out.println("clazz.getGenericSuperclass() : " + parameterizedType);
        if (parameterizedType != null) {
            System.out.println("parameterizedType : " + parameterizedType);
            System.out.println("parameterizedType.getClass() : " + parameterizedType.getClass());
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
                    System.out.println("--------- topType is TypeVariable  : " + tv);
                    System.out.println("topType.getBounds() : " + Arrays.stream(tv.getBounds()).collect(Collectors.toList()));
                    System.out.println("topType.getName() : " + tv.getName());
                    System.out.println("topType.getTypeName() : " + tv.getTypeName());
                    System.out.println("topType.getAnnotatedBounds() : " + Arrays.stream(tv.getAnnotatedBounds()).collect(Collectors.toList()));
                    System.out.println("topType.getAnnotations() : " + Arrays.stream(tv.getAnnotations()).collect(Collectors.toList()));
                    System.out.println("topType.getGenericDeclaration() : " + tv.getGenericDeclaration());
                } else if (Class.class.isAssignableFrom(type.getClass())) {
                    System.out.println("--------- topType is Class  : " + type);
                    System.out.println("topType.getTypeName() : " + type.getTypeName());
                    System.out.println("topType.getClass() : " + type.getClass());
                } else if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                    ParameterizedType topType = (ParameterizedType) type;
                    System.out.println("--------- topType is ParameterizedType : " + topType);
                    System.out.println("topType.getTypeName() : " + topType.getTypeName());
                    System.out.println("topType.getRawType() : " + topType.getRawType());
                    System.out.println("topType.getOwnerType() : " + topType.getOwnerType());
                    Type[] subParameterizeTypes = topType.getActualTypeArguments();
                    System.out.println("topType.getActualTypeArguments().length : " + subParameterizeTypes.length);
                    for (Type subType : subParameterizeTypes) {
                        System.out.println("------------------ subType : " + subType);
                        System.out.println("    subType.getTypeName() : " + subType.getTypeName());
                        System.out.println("    subType.getClass() : " + subType.getClass());
                        if (TypeVariable.class.isAssignableFrom(subType.getClass())) {
                            TypeVariable tv = (TypeVariable) subType;
//                        System.out.println("------------------------------------ tv : " + tv);
                            System.out.println("    subType is [ TypeVariable ]");
                            System.out.println("    subType.getName() : " + tv.getName());
                            System.out.println("    subType.getAnnotatedBounds() : " + PrintUtils.toList(tv.getAnnotatedBounds()));
                            System.out.println("    subType.getBounds() : " + Arrays.stream(tv.getBounds()).collect(Collectors.toList()));
                            System.out.println("    subType.getGenericDeclaration() : " + tv.getGenericDeclaration());
                        } else if (Class.class.isAssignableFrom(subType.getClass())) {
                            System.out.println("    subType is [ Class ]  : " + subType);
                            System.out.println("    subType.getTypeName() : " + subType.getTypeName());
                            System.out.println("    subType.getClass() : " + subType.getClass());
                        } else if (WildcardType.class.isAssignableFrom(subType.getClass())) {
                            WildcardType wt = (WildcardType) subType;
                            System.out.println("    subType is [ WildcardType ]");
                            System.out.println("    subType.getLowerBounds() : " + Arrays.stream(wt.getLowerBounds()).collect(Collectors.toList()));
                            System.out.println("    subType.getUpperBounds() : " + Arrays.stream(wt.getUpperBounds()).collect(Collectors.toList()));
                        } else if (ParameterizedType.class.isAssignableFrom(subType.getClass())) {
                            ParameterizedType spt = (ParameterizedType) subType;
                            System.out.println("    subType is [ ParameterizedType ]");
                            System.out.println("    subType.getRawType() : " + spt.getRawType());
                            System.out.println("    subType.getOwnerType() : " + spt.getOwnerType());
                            System.out.println("    subType.getActualTypeArguments() : " +
                                    Arrays.stream(spt.getActualTypeArguments()).collect(Collectors.toList()));

                            Type[] threeTypes = spt.getActualTypeArguments();

                            for (Type threeType : threeTypes) {
                                System.out.println("------------------ threeType : " + threeType);
                                System.out.println("        threeType.getTypeName() : " + threeType.getTypeName());
                                System.out.println("        threeType.getClass() : " + threeType.getClass());
                                if (TypeVariable.class.isAssignableFrom(threeType.getClass())) {
                                    TypeVariable ttv = (TypeVariable) threeType;
                                    System.out.println("        threeType is [ TypeVariable ]");
                                    System.out.println("        threeType.getName() : " + ttv.getName());
                                    System.out.println("        threeType.getAnnotatedBounds() : " + PrintUtils.toList(ttv.getAnnotatedBounds()));
                                    System.out.println("        threeType.getAnnotations() : " + PrintUtils.toList(ttv.getAnnotations()));
                                    System.out.println("        threeType.getDeclaredAnnotations() : " + PrintUtils.toList(ttv.getDeclaredAnnotations()));
                                    System.out.println("        threeType.getBounds() : " + Arrays.stream(ttv.getBounds()).collect(Collectors.toList()));
                                    for (Type threeBound : ttv.getBounds()) {
                                        System.out.println("        threeType.getBounds() -- ");
                                        System.out.println("        threeType.getBounds()[].getClass() : " + threeBound.getClass());
                                        System.out.println("        threeType.getBounds() ++ ");
                                    }
                                    System.out.println("        threeType.getGenericDeclaration() : " + ttv.getGenericDeclaration());
                                } else if (Class.class.isAssignableFrom(threeType.getClass())) {
                                    System.out.println("        threeType is [ Class ]  : " + threeType);
                                    System.out.println("        threeType.getTypeName() : " + threeType.getTypeName());
                                    System.out.println("        threeType.getClass() : " + threeType.getClass());
                                } else if (WildcardType.class.isAssignableFrom(threeType.getClass())) {
                                    WildcardType twt = (WildcardType) threeType;
                                    System.out.println("        threeType is [ WildcardType ]");
                                    System.out.println("        threeType.getLowerBounds() : " + Arrays.stream(twt.getLowerBounds()).collect(Collectors.toList()));
                                    System.out.println("        threeType.getUpperBounds() : " + Arrays.stream(twt.getUpperBounds()).collect(Collectors.toList()));
                                } else if (ParameterizedType.class.isAssignableFrom(threeType.getClass())) {
                                    ParameterizedType tpt = (ParameterizedType) threeType;
                                    System.out.println("        tpt is [ ParameterizedType ]");
                                    System.out.println("        tpt.getRawType() : " + tpt.getRawType());
                                    System.out.println("        tpt.getOwnerType() : " + tpt.getOwnerType());
                                    System.out.println("        tpt.getActualTypeArguments() : " +
                                            Arrays.stream(tpt.getActualTypeArguments()).collect(Collectors.toList()));
                                }
                            }

                        }
                    }
                }
            }
        }
        System.out.println("++++++++++ clazz.getGenericInterfaces() +++++++++");
        Type[] interfaceTypes = clazz.getGenericInterfaces();
        for (Type interfaceType : interfaceTypes) {
            System.out.println("interfaceType.getTypeName() : " + interfaceType.getTypeName());
            System.out.println("interfaceType.getClass() : " + interfaceType.getClass());
        }

        System.out.println("========= class parameterize info end");

        System.out.println("========= this class interface info ==== ");
        System.out.println("this class interface size : " + clazz.getInterfaces().length);
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
//            System.out.println(anInterface + " , extend interface size : " + anInterface.getInterfaces().length);
            displayClassInfo(anInterface);
        }
        System.out.println("=========== super class interface info ======== ");
        if (clazz.getSuperclass() != null) {
            Class[] sis = clazz.getSuperclass().getInterfaces();
            System.out.println("super class interface size : " + sis.length);
            for (Class si : sis) {
//            System.out.println(si + " , extend interface size : " + si.getInterfaces().length);
                displayClassInfo(si);
            }
        } else {
            System.out.println("super class is null");
        }
    }

    public static void displayMethod(Method method) {
        System.out.println("========================================== method : " + method);
        System.out.println("method.getName() : " + method.getName());
        System.out.println("method.isBridge() : " + method.isBridge());
        System.out.println("method.isSynthetic() : " + method.isSynthetic());
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
                System.out.println("ppt.getActualTypeArguments().length : " + ppt.getActualTypeArguments().length);
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

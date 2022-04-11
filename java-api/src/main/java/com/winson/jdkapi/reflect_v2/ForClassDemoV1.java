package com.winson.jdkapi.reflect_v2;

import com.winson.utils.common.PrintUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class ForClassDemoV1 {

    public static void main(String[] args) {

        Class targetObjClass = TargetObj.class;
        displayClass(targetObjClass);
        Class targetArrayObjClass = TargetObj[].class;
        displayClass(targetArrayObjClass);
        Class myEnumClass = TargetObj.MyEnum.class;
        displayClass(myEnumClass);
        Class myStaticObjClass = TargetObj.MyStaticObj.class;
        displayClass(myStaticObjClass);
        Class myNormalObjClass = TargetObj.MyNormalObj.class;
        displayClass(myNormalObjClass);

        TargetObj myMethodClass = new TargetObj() {

        };
        Class methodClass = myMethodClass.getClass();
        displayClass(methodClass);
        Class myInterfaceObjClass = TargetObj.MyInterfaceObj.class;
        displayClass(myInterfaceObjClass);

    }

    public static void displayClass(Class clazz) {
        System.out.println("=========== " + clazz.getSimpleName() + ".class ===========");
        System.out.println("clazz.getName(): " + clazz.getName());
        System.out.println("clazz.getSimpleName(): " + clazz.getSimpleName());
        System.out.println("clazz.getPackage(): " + clazz.getPackage());
        System.out.println("clazz.getCanonicalName(): " + clazz.getCanonicalName());
        System.out.println("clazz.getComponentType(): " + clazz.getComponentType());
        System.out.println("clazz.getClassLoader(): " + clazz.getClassLoader());
        System.out.println("clazz.getDeclaringClass(): " + clazz.getDeclaringClass());
        System.out.println("clazz.getAnnotations(): " + PrintUtils.toList(clazz.getAnnotations()));
        System.out.println("clazz.getDeclaredAnnotations(): " + PrintUtils.toList(clazz.getDeclaredAnnotations()));
        System.out.println("clazz.getInterfaces(): " + PrintUtils.toList(clazz.getInterfaces()));
        System.out.println("clazz.getEnclosingClass(): " + clazz.getEnclosingClass());
        System.out.println("clazz.getEnclosingMethod(): " + clazz.getEnclosingMethod());
        System.out.println("clazz.getEnclosingConstructor(): " + clazz.getEnclosingConstructor());
        System.out.println("clazz.getEnumConstants(): " + Arrays.stream(Optional.ofNullable(clazz.getEnumConstants())
                .orElse(new Object[0])).collect(Collectors.toList()));
        System.out.println("clazz.getTypeName(): " + clazz.getTypeName());
        System.out.println("clazz.getSuperclass(): " + clazz.getSuperclass());
        System.out.println("clazz.getModifiers(): " + clazz.getModifiers());
        System.out.println("clazz.isInterface(): " + clazz.isInterface());
        System.out.println("clazz.isArray(): " + clazz.isArray());
        System.out.println("clazz.isAnnotation(): " + clazz.isAnnotation());
        System.out.println("clazz.isEnum(): " + clazz.isEnum());
        System.out.println("clazz.isPrimitive(): " + clazz.isPrimitive());
        System.out.println("clazz.isSynthetic(): " + clazz.isSynthetic());
        System.out.println("clazz.isLocalClass(): " + clazz.isLocalClass());
        System.out.println("clazz.isMemberClass(): " + clazz.isMemberClass());
        System.out.println("clazz.getTypeParameters(): " + Arrays.stream(Optional.ofNullable(clazz.getTypeParameters())
                .orElse(new TypeVariable[0])).collect(Collectors.toList()));

        System.out.println();
    }

}

package com.winson.jdkapi.reflect_v2;

import com.winson.utils.common.PrintUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class ForGenericInfoDemoV1 {

    public static void main(String[] args) {

        Class clazz = TargetObj.class;
        displayGenericInfo(clazz);
    }

    public static void displayGenericInfo(Class clazz) {
        System.out.println(clazz);
        System.out.println("clazz.getGenericSuperclass(): " + clazz.getGenericSuperclass());
        System.out.println("clazz.getGenericInterfaces(): " + PrintUtils.toList(clazz.getGenericInterfaces()));
        System.out.println("======== GenericSuperclass ========");
        displayGenericTypeInfo(clazz.getGenericSuperclass());
        System.out.println("======== GenericInterfaces ========");
        for (Type type : clazz.getGenericInterfaces()) {
            displayGenericTypeInfo(type);
        }
    }

    public static void displayGenericTypeInfo(Type type) {
        System.out.println("type:" + type);
        System.out.println("type.getClass(): " + type.getClass());
        System.out.println("type.getTypeName(): " + type.getTypeName());
        if (type instanceof ParameterizedTypeImpl) {
            ParameterizedTypeImpl pt = (ParameterizedTypeImpl) type;
            System.out.println("ParameterizedTypeImpl ***** ");
            System.out.println("pt:" + type);
            System.out.println("pt.getOwnerType(): " + pt.getOwnerType());
            System.out.println("pt.getRawType(): " + pt.getRawType());
            System.out.println("pt.getClass(): " + pt.getClass());
            System.out.println("pt.getTypeName(): " + pt.getTypeName());
            System.out.println("pt.getActualTypeArguments(): " + PrintUtils.toList(pt.getActualTypeArguments()));
            for (Type at : pt.getActualTypeArguments()) {
                System.out.println("at:" + at);
                System.out.println("at.getClass(): " + at.getClass());
                System.out.println("at.getTypeName(): " + at.getTypeName());
            }
        } else if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            wt.getTypeName();
            wt.getClass();
            wt.getLowerBounds();
            wt.getUpperBounds();
        } else if (type instanceof TypeVariable) {
            TypeVariable tv = (TypeVariable) type;
            tv.getTypeName();
            tv.getAnnotations();
            tv.getClass();
            tv.getAnnotatedBounds();
            tv.getBounds();
            tv.getDeclaredAnnotations();
        } else if (type instanceof Class) {

        }
        System.out.println();
    }

}

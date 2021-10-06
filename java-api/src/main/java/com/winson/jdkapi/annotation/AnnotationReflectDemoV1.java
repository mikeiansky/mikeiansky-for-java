package com.winson.jdkapi.annotation;

/**
 * @author winson
 * @date 2021/10/6
 **/
@UserGroup(name = "wenxiang", age = 33)
public class AnnotationReflectDemoV1 {

    public static void main(String[] args) {

        UserGroup userGroup = AnnotationReflectDemoV1.class.getAnnotation(UserGroup.class);
        System.out.println(userGroup);
        System.out.println(userGroup.name());
        System.out.println(userGroup.age());

    }

}

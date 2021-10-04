package com.winson.jdkapi.reflect;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/10/4
 **/
@Documented
@Target({ElementType.PARAMETER, ElementType.TYPE_PARAMETER, ElementType.FIELD,
        ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RootAnnotation("normal-reflect-annotation-inherited")
@RootAnnotationWithInherited("reflect-annotation-inherited")
public @interface ReflectAnnotation {

    String value() default "reflect-annotation";

}

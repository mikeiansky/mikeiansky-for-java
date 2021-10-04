package com.winson.jdkapi.reflect;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/10/4
 **/
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RootAnnotationWithInherited {

    String value() default "root-annotation";

}

package com.winson.jdkapi.reflect.v1;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/10/4
 **/
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface RootAnnotation {

    String value() default "normal-root-annotation";

}

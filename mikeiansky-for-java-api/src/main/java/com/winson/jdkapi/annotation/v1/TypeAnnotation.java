package com.winson.jdkapi.annotation.v1;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/6/14
 **/
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD,
        ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeAnnotation {

    String value() default "winson";

}

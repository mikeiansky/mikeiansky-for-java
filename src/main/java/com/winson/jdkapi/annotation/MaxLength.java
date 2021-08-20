package com.winson.jdkapi.annotation;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/6/15
 **/
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength {

    int value() default 10;

}

package com.winson.jdkapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author winson
 * @date 2021/6/14
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@TypeAnnotation(value = "all-target")
@Inherited
public @interface AllTargetAnnotation {

    String address() default "shenzhen";

    String value() default "zwx";

}

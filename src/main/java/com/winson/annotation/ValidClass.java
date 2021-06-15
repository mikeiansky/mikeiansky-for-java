package com.winson.annotation;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/6/15
 **/
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidClass {

    Class targetClass();

}

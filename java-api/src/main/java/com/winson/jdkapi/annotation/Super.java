package com.winson.jdkapi.annotation;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2021/6/20
 **/
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited // 代表能被子类继承
public @interface Super {

}

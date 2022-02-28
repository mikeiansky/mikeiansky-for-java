package com.winson.jdkapi.annotation;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2022/2/28
 **/
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited // 代表能被子类继承
public @interface HolySuper {
}

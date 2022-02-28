package com.winson.jdkapi.reflect;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2022/2/28
 **/
@Documented
@Target({ElementType.PARAMETER, ElementType.TYPE_PARAMETER, ElementType.FIELD,
        ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NormalAnnotation {
}

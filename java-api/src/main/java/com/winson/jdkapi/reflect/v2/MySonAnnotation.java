package com.winson.jdkapi.reflect.v2;

import java.lang.annotation.*;

/**
 * @author winson
 * @date 2022/4/11
 **/
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@MyFatherAnnotation
@Inherited
public @interface MySonAnnotation {

}

package com.winson.annotation;

/**
 * @author winson
 * @date 2021/6/15
 **/
public interface AnnotationService {

    void addUser(@MaxLength(20) @NotEmpty String userName);

}

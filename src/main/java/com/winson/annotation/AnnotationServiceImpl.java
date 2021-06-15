package com.winson.annotation;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class AnnotationServiceImpl implements AnnotationService{

    public void addUser(@NotEmpty String userName){
        System.out.println("add user");
    }

}

package com.winson.annotation;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class AnnotationServiceImpl implements AnnotationService {

    public void addUser(String userName) {
        System.out.println("add user");
    }

    @Override
    public void editUser(AnnotationUser user) {
        System.out.println("impl edit user name");
    }

}

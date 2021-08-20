package com.winson.jdkapi.annotation;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class AnnotationServiceImpl implements AnnotationService {

    public void addUser(String userName) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add user");
    }

    @Override
    public void editUser(AnnotationUser user) {
        System.out.println("impl edit user name");
    }

    @Override
    public void addFriend(String country, int size, String... friends) {
        System.out.println("add friend : " + country + " , size : " + size + " , friends : [" + String.join(",", friends) + "]");
    }

}

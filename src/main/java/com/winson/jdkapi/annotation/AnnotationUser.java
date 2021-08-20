package com.winson.jdkapi.annotation;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class AnnotationUser extends Person{

    public String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

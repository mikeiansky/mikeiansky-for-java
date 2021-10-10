package com.winson.jdkapi.annotation;

/**
 * @author winson
 * @date 2021/6/15
 **/
@UserGroup
public class Person {


    public int age;

    private int height;

    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

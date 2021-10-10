package com.winson.pojo;

/**
 * @author winson
 * @date 2021/10/10
 **/
public class Labrador extends Dog{

    @Override
    public void eat() {
        System.out.println("labrador eat");
    }

    public void bark(){
        System.out.println("Labrador is barking ... ");
    }

}

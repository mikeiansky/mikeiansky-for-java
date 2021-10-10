package com.winson.pojo;

/**
 * @author winson
 * @date 2021/10/10
 **/
public class Dog extends Animal{

    @Override
    public void eat() {
        System.out.println("dog eat meet");
    }

    public void run(){
        System.out.println("dog running ... ");
    }

}

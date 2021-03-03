package com.winson.basic;

/**
 * @author winson
 * @date 2021/2/4
 **/
public class TestBasic {

    public static class MyStatic{
        public static final String name = "my_static";
        public static void say(){
            System.out.println("haha");
        }
    }

    public static void main(String[] args) {
        MyStatic myStatic = new MyStatic();
//        myStatic.hashCode();
//        MyStatic.say();
        myStatic.say();
        System.out.println(myStatic.name);
    }

}

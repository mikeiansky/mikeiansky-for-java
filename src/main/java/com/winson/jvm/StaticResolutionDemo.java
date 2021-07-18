package com.winson.jvm;

/**
 * @author winson
 * @date 2021/7/18
 **/
public class StaticResolutionDemo {

    static class Human {

    }

    static class Man extends Human{

    }

    static class Woman extends Human{

    }

    public void sayHello(Human human){
        System.out.println("hello i am guy");
    }

    public void sayHello(Man man){
        System.out.println("hello i am man");
    }

    public void sayHello(Woman woman){
        System.out.println("hello i am woman");
    }

    public static void main(String[] args) {

        Human man = new Man();
        Human woman = new Woman();

        StaticResolutionDemo demo = new StaticResolutionDemo();
        demo.sayHello(man);
        demo.sayHello(woman);

    }

}

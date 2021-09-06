package com.winson.jdkapi.juc.version_1;

public class TestSynchronized {

    public static class Person{

        public void sayHello(){
            System.out.println("hello this @Person : " + this);
        }

    }

    public static class Child extends Person{
        @Override
        public void sayHello() {
            System.out.println("hello this @child : " + this);
            super.sayHello();
        }
    }

    public static void main(String[] args) {

        Child child = new Child();
        child.sayHello();


    }

}

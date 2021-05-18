package com.winson.syntax.overload;

/**
 * @author winson
 * @date 2021/5/18
 **/
public class TestOverLoad {

    public static class Car{
        public static void run(String msg){
            System.out.println("car run... " + msg);
        }
    }

    public static class Benz extends Car {
        public static void run(int speed){
            System.out.println("benz run... " + speed);
        }
    }

    public static void main(String[] args) {
        System.out.println("test over load start ... ");

        Car car = new Car();
        car.run("shenzhen");
        Car.run("shenzhen");

        Benz.run("shenzhen");
        Benz.run(20);

        System.out.println("test over load end ... ");
    }

}

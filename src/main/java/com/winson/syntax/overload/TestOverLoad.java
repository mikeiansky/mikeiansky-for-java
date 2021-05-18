package com.winson.syntax.overload;


/**
 * @author winson
 * @date 2021/5/18
 **/
public class TestOverLoad {

    public static class Machine{
        public void make(String msg){
            System.out.println("machine make ... " + msg);
        }
    }

    public static class Car extends Machine{

//        public static void run(String msg){
//            System.out.println("car run... " + msg);
//        }
//
//        public void move(int distance){
//            System.out.println("car move distance : " + distance);
//        }

        public final void make(String msg){
            System.out.println("car make ... " + msg);
        }

    }

    public static class Benz extends Car {
//        public static void run(String speed){
//            System.out.println("benz run... " + speed);
//        }
//
//        public void move(double distance){
//            System.out.println("benz move distance : " + distance);
//        }
//        public void make(String msg){
//            System.out.println("benz make ... " + msg);
//        }

    }

    public static void main(String[] args) {
//        Car.run("shenzhen");
//        Benz.run("shenzhen");

//        Benz benz = new Benz();
//        benz.move(1.1);
//        benz.move(2);
//
//        Random random = new Random();
//        random.nextDouble();

        Machine machine = new Benz();
        machine.make("my menz");

//        System.out.println("test over load end ... ");
    }

}

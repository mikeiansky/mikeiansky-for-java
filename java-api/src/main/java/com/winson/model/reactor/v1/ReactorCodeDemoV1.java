package com.winson.model.reactor.v1;

/**
 * @author winson
 * @date 2022/6/7
 **/
public class ReactorCodeDemoV1 implements Runnable {

    public ReactorCodeDemoV1 combine(Runnable first, Runnable second) {
        return new ReactorCodeDemoV1() {
            @Override
            public void run() {
                System.out.println("this-01 :: " + this);
                System.out.println("ReactorCodeDemoV1.this-01 :: " + ReactorCodeDemoV1.this);
                ReactorCodeDemoV1.this.run();
                first.run();
                second.run();
            }
        };
    }

    public ReactorCodeDemoV1 sayHello(Runnable runnable, String msg) {
        return new ReactorCodeDemoV1() {
            @Override
            public void run() {
                System.out.println("before action run , say msg : " + msg);
                runnable.run();
                System.out.println("this-02 :: " + this);
                System.out.println("ReactorCodeDemoV1.this-02 :: " + ReactorCodeDemoV1.this);
                ReactorCodeDemoV1.this.run();
            }
        };
    }

    public static void submit(Runnable action) {
        action.run();
    }


    public static void main(String[] args) {
//        System.out.println(" test reactor ... ");
        new ReactorCodeDemoV1()
                .combine(
                        () -> System.out.println("action one execute ... "),
                        () -> System.out.println("action two execute ... ")
                ).sayHello(
                        () -> System.out.println("action sayHello ready!"), "study")
                .run();
    }

    @Override
    public void run() {
//        System.out.println("---- ----");
    }

}

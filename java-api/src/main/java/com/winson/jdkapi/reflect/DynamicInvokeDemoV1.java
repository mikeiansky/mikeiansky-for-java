package com.winson.jdkapi.reflect;

/**
 * @author winson
 * @date 2022/2/24
 **/
public class DynamicInvokeDemoV1 {

    public static class One {

    }

    public static class Two extends One {

    }

    public static class Three extends Two {

    }

    public void sayHello(One one){
        System.out.println("sayHello1 one : " + one);
    }

    public void sayHello(Two two){
        System.out.println("sayHello2 two : " + two);
    }

    public void sayHello(Three three){
        System.out.println("sayHello3 three : " + three);
    }

    public static void main(String[] args) {

        DynamicInvokeDemoV1 app = new DynamicInvokeDemoV1();
        app.sayHello(new One());
        app.sayHello(new Two());
        app.sayHello(new Three());
        System.out.println("-----");

        One two = new Two();
        One three = new Three();
        Two ot = new Three();
        app.sayHello(two);
        app.sayHello(three);
        app.sayHello(ot);

    }

}

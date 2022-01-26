package com.winson.jvm.initialize;

/**
 * @author winson
 * @date 2022/1/23
 **/
public class InterfaceInitializeDemo2 {

    static {
        System.out.println("InterfaceInitializeDemo2 init !");
    }

    public static class InnerParent {
        static {
            System.out.println("InnerParent class init!");
        }
    }

    public static class Inner extends InnerParent implements Hello {
        static {
            System.out.println("Inner class init!");
        }

        @Override
        public void sayHello() {
            System.out.println("inner sayHello");
        }
    }

    public static class Inner2 {
        static {
            System.out.println("Inner2 class init!");
        }
    }

    public interface Hello {

        Inner2 inner = new Inner2();

        void sayHello();
    }

    public static void main(String[] args) {

//        System.out.println(Inner.class);
        Inner inner = new Inner();
        inner.sayHello();
        System.out.println(Hello.inner);

    }

}

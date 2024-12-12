package io.github.mikeiansky.java.base.jdk.clazz;

/**
 * @author mike ian
 * @date 2024/12/5
 * @desc
 **/
public class ClazzEqualDemo {

    public static class Order {

    }

    public static class OneOrder extends Order {

    }


    public static void main(String[] args) {
        Class orderClazz = Order.class;
        Class oneOrderClazz = OneOrder.class;
        System.out.println(orderClazz);
        System.out.println(oneOrderClazz);
        System.out.println("orderClazz == Order.class ==> " + (orderClazz == Order.class));
        System.out.println("oneOrderClazz == OneOrder.class ==> " + (oneOrderClazz == OneOrder.class));
        System.out.println("orderClazz == oneOrderClazz ==> " + (orderClazz == oneOrderClazz));
        System.out.println("oneOrderClazz == Order.class ==> " + (oneOrderClazz == Order.class));
    }

}
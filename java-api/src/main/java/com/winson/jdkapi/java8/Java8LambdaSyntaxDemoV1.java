package com.winson.jdkapi.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author winson
 * @date 2021/10/4
 **/
public class Java8LambdaSyntaxDemoV1 {

    public static class Temp {

        public Temp() {
            System.out.println("temp default construct");
        }

        public Temp(String source) {
            System.out.println("temp construct source : " + source);
        }

        public static String staticInvoke(String msg) {
            System.out.println("temp static invoke msg : " + msg);
            return msg;
        }

        public String selfInvoke(String msg) {
            System.out.println("temp self invoke msg : " + msg);
            return msg;
        }

        public void selfInvokeEmpty() {
            System.out.println("temp self invoke empty !");
        }

        public void invokeOtherTemp(Temp other){
            System.out.println("invoke other temp other is : " + other);
        }

    }


    public static void main(String[] args) {

        Temp temp = new Temp();

        List<String> list = Arrays.asList("1", "2", "3", "4");

        // invoke by temp object method , in things is method argument
//        list.stream().map(temp::selfInvoke).forEach(System.out::println);

        // invoke by Temp class static method , in things is method argument
//        list.stream().map(Temp::staticInvoke).forEach(System.out::println);

        // invoke by Temp object construct(string) , in things is construct argument
//        list.stream().map(Temp::new).forEach(System.out::println);

        List<Temp> tempList = new ArrayList<>();
        tempList.add(temp);
        tempList.add(temp);
        tempList.add(temp);
        tempList.add(temp);

        // invoke by temp object method , in things is temp object ,
//        tempList.stream().forEach(Temp::selfInvokeEmpty);

        // invoke by temp object , in things is method argument
        tempList.stream().forEach(temp::invokeOtherTemp);

    }

}

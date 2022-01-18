package com.winson.base;

/**
 * @author winson
 * @date 2022/1/17
 **/
public class ExtendsDemo {

    public static class One {

        protected String flag = "nihao-one";

        public One(){
            System.out.println("print on one : "+this.flag);
        }
    }

    public static class Two extends One{

        protected String flag = "nihao-two";

        public Two(){
            System.out.println("print on two : " + this.flag);
        }

    }

    public static void main(String[] args) {

        Two two = new Two();

    }

}

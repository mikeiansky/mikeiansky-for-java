package com.winson.genericity;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class MultiGenericityDemo {

    public void createDoc(String json){
        System.out.println("create doc from json");
    }

    public void createDoc(Object... objects){
        System.out.println("create doc from object");
    }

    public static Object createSource(){
        return "{\"name\":\"ciwei\"}";
    }

    public static void main(String[] args) {

        MultiGenericityDemo demo = new MultiGenericityDemo();
        demo.createDoc(createSource());

    }

}

package com.winson.jdkapi.io.other;

import java.io.*;

/**
 * @author winson
 * @date 2021/8/28
 **/
public class ObjectIODemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        MyBean myBean = new MyBean();
//        myBean.index = 2;
//        System.out.println(myBean);
//
////        Second second = new Second();
////        second.flag = "hello";
//
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\out.obj"));
//        out.writeObject(myBean);
////        out.writeObject(second);
//        out.flush();


//        System.out.println("write success !");


//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\out.obj"));
//        MyBean first = (MyBean) in.readObject();
//        System.out.println(first);
//        System.out.println(first.index);

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\out2.obj"));
        Second second1 = (Second) in.readObject();
        System.out.println(in);
        System.out.println(second1);
        System.out.println(second1.flag);

        ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("D:\\out2.obj"));
        Second second2 = (Second) in2.readObject();
        System.out.println(in2);
        System.out.println(second2);

        System.out.println(second2 == second1);


    }

}

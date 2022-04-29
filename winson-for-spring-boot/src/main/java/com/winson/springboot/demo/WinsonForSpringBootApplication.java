package com.winson.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class WinsonForSpringBootApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WinsonForSpringBootApplication.class, args);
        MyService myService = context.getBean(MyService.class);
//        myService.doTransaction();
//        System.out.println("----- split --------");
//        myService.doTransaction();
        System.out.println("hahahah ");
//        myService.testLibTwo();
//        System.exit(0);

        WinsonMapper winsonMapper = context.getBean(WinsonMapper.class);

//        System.out.println("winson mapper : " + winsonMapper);

//        winsonMapper.update();
//        System.out.println(" ----------1 ");
//        winsonMapper.update();
//        System.out.println(" ----------2 ");
//        winsonMapper.update();
//        System.out.println(" ----------3 ");
        winsonMapper.update();

//        int length = 1;
//        Thread[] threads = new Thread[length];
//
//        for (int i = 0; i < threads.length; i++) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    winsonMapper.update();
//                }
//            });
//            thread.start();
//            threads[i] = thread;
//        }
//
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
        System.exit(-1);
    }

}

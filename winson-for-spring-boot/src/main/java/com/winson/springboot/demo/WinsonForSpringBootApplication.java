package com.winson.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class WinsonForSpringBootApplication {



    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WinsonForSpringBootApplication.class, args);
        MyService myService = context.getBean(MyService.class);
        myService.doTransaction();
        System.out.println("----- split --------");
        myService.doTransaction();
        System.out.println("hahahah ");
//        WinsonMapper winsonMapper = context.getBean(WinsonMapper.class);
//        System.out.println("winson mapper : " + winsonMapper);
//        winsonMapper.update();

        System.exit(-1);
    }

}

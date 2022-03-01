package com.winson.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WinsonForSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WinsonForSpringBootApplication.class, args);
        context.getBean(MyService.class);

    }

}

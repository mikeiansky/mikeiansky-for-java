package com.winson.spring.boot.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author mike ian
 * @date 2023/6/7
 * @desc
 **/
@SpringBootApplication
public class MySpringDockerApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MySpringDockerApp.class, args);


        
    }

}

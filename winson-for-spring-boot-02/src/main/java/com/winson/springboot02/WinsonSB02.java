package com.winson.springboot02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author mike ian
 * @date 2023/6/5
 * @desc
 **/
@SpringBootApplication
public class WinsonSB02 {

    @Value("${spring.application.name:zero}")
    private String name;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WinsonSB02.class, args);
        System.out.println(applicationContext.getEnvironment().getProperty("hello"));
        System.out.println(applicationContext.getEnvironment().getProperty("act.name"));
        System.out.println(applicationContext.getEnvironment().getProperty("sms.code"));
        System.out.println(applicationContext.getEnvironment().getProperty("sms.code22"));
        WinsonSB02 winsonSB02 = applicationContext.getBean(WinsonSB02.class);
        System.out.println(winsonSB02);
        System.out.println(winsonSB02.name);
        System.out.println(applicationContext.getBean(MyService.class));

    }

}

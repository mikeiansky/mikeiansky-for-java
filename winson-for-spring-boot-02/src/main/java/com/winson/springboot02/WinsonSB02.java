package com.winson.springboot02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * @author mike ian
 * @date 2023/6/5
 * @desc
 **/
@SpringBootApplication
public class WinsonSB02 {

    @Value("${spring.application.name:zero}")
    private String name;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WinsonSB02.class, args);
//        System.out.println("hello="+applicationContext.getEnvironment().getProperty("hello"));
//        System.out.println("act.name="+applicationContext.getEnvironment().getProperty("act.name"));
//        System.out.println("sms.code="+applicationContext.getEnvironment().getProperty("sms.code"));
//        System.out.println("sms.code22="+applicationContext.getEnvironment().getProperty("sms.code22"));
//        System.out.println("first="+applicationContext.getEnvironment().getProperty("first"));
//        System.out.println("second="+applicationContext.getEnvironment().getProperty("second"));

//        MyTransactionService myTransactionService = applicationContext.getBean(MyTransactionService.class);
//        myTransactionService.hello("winson");


        ZKApp zkApp = applicationContext.getBean(ZKApp.class);
        zkApp.connectZKServer();
    }

}

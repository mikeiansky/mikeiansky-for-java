package com.winson.springboot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author winson
 * @date 2022/6/22
 **/
@SpringBootApplication
public class Slf4jLoggerDemoApp {

    public static Logger logger = LoggerFactory.getLogger(Slf4jLoggerDemoApp.class);

    public static void main(String[] args) {

        SpringApplication.run(Slf4jLoggerDemoApp.class);
        logger.info("application start1 ... ");
        logger.info("application start2 ... ");

    }

}

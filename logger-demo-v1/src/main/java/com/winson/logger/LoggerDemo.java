package com.winson.logger;

import org.apache.log4j.Logger;

/**
 * @author winson
 * @date 2021/9/6
 **/
public class LoggerDemo {

    private static final Logger logger = Logger.getLogger(LoggerDemo.class);

    public static void main(String[] args) {

        logger.info("Hello World!");


    }

}

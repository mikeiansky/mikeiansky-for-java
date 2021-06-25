package com.winson.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class Log4JVersion2Demo {

    private static final Logger logger = LogManager.getLogger("demo");

    public static void main(String[] args) {
        System.out.println("----1");

//        System.out.println(logger.getClass().getResource("/").getPath());

//        System.out.println(Log4JVersion2Demo.class.getName());
//        while (1==1){
            logger.trace("Hello World - trace");
            logger.debug("Hello World - Debug");
            logger.debug("Hello World - Debug2");
            logger.info("Hello-World-Info");
            logger.error("Hello World - error : {}" ,"this is error message ... ");
//        }


//        System.out.println("----2");

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}

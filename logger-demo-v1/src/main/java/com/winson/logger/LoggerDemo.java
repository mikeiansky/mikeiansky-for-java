package com.winson.logger;

//import org.apache.log4j.Logger;


import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author winson
 * @date 2021/9/6
 **/
public class LoggerDemo {

//    private static final Logger logger = Logger.getLogger(LoggerDemo.class);
//    private static final Logger logger = Logger.getLogger(LoggerDemo.class);
    private static final Logger logger = LogManager.getLogger(LoggerDemo.class);

    public static void main(String[] args) {
//        logger.traceEntry();
        logger.trace("Hello World!");
//        logger.debug("Hello World! a={},b={}");

        logger.info("p1:{}, p2:{}, p3:{}, p4:{}", 1,2,3,4);

    }

}

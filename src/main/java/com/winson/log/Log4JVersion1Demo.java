package com.winson.log;

import org.apache.log4j.Logger;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class Log4JVersion1Demo {

    private static Logger logger = Logger.getLogger(Log4JVersion1Demo.class);

    public static void main(String[] args) {
        logger.debug("Hello World debug");
        logger.info("Hello World info msg");
        logger.error("Hello World error");
    }

}

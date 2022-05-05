package com.winson.opensource.logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author winson
 * @date 2021/12/27
 **/
public class JNDIServer {

    public static void main(String[] args) throws NamingException {
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        String url = "rmi://127.0.0.1:1099/ExportObject";
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(url);

    }

}

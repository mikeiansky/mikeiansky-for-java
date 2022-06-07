package com.winson.jdkapi.rmi.v1.rmi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author winson
 * @date 2021/12/28
 **/
public class HelloClient {

    public HelloDefine hello;

    public void client() throws MalformedURLException, NotBoundException, RemoteException, NamingException {

        hello = (HelloDefine) Naming.lookup("rmi://localhost:8888/hello");
        System.out.println("client:");
        System.out.println("hello : " + hello);
        System.out.println("hello : " + hello.getClass());

        System.out.println(hello.helloWorld());
        System.out.println(hello.sayHello("ciwei"));
        hello.exec("calc");


//        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
//        Context context = new InitialContext();
//        context.lookup("rmi://127.0.0.1/exec");
    }

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, NamingException {
//        HelloClient client = new HelloClient();
//        client.client();

        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
//        String url = "rmi://localhost:1099/obj";
//        InitialContext initialContext = new InitialContext();
//        initialContext.lookup(url);


        InitialContext ctx = new InitialContext();
        ctx.lookup("rmi://172.16.2.113:1099/cvywm1");
//        ctx.lookup("ldap://172.16.2.113:1389/ddpugv");


    }

}

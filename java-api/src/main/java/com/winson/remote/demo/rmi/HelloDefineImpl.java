package com.winson.remote.demo.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author winson
 * @date 2021/12/28
 **/
public class HelloDefineImpl extends UnicastRemoteObject implements HelloDefine {

    private static final long serialVersionUID = 1L;

    protected HelloDefineImpl() throws RemoteException {
        super();
    }

    @Override
    public String helloWorld() throws RemoteException {
        return "Hello Winson";
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello [" + name + " ] !";
    }

    @Override
    public void exec(String cmd) throws RemoteException {
        try {
            System.out.println("exec start");
            Runtime.getRuntime().exec(cmd);
            System.out.println("exec success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

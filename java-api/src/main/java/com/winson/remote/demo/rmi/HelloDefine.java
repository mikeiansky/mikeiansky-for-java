package com.winson.remote.demo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author winson
 * @date 2021/12/28
 **/
public interface HelloDefine extends Remote {

    String helloWorld() throws RemoteException;

    String sayHello(String name) throws RemoteException;

    void exec(String cmd) throws RemoteException;

}

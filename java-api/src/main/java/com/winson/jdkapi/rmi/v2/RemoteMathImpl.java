package com.winson.jdkapi.rmi.v2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2022/4/27
 **/
public class RemoteMathImpl extends UnicastRemoteObject implements IRemoteMath {

    private AtomicInteger hit = new AtomicInteger();

    protected RemoteMathImpl() throws RemoteException {

    }

    @Override
    public int add(int one, int two) throws RemoteException {
        System.out.println("add one : " + one + " , two : " + two + " , hit : " + hit.getAndIncrement());
        return one + two;
    }

    @Override
    public void sayHello(String msg) throws RemoteException {
        System.out.println("sayHello , hit : " + hit.getAndIncrement() + " , msg : " + msg);
        System.out.println("hello : " + msg);
    }
}

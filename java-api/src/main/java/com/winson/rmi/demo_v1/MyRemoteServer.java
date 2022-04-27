package com.winson.rmi.demo_v1;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author winson
 * @date 2022/4/27
 **/
public class MyRemoteServer {

    public static void main(String[] args) {

        try {
            IRemoteMath remoteMath = new RemoteMathImpl();
            LocateRegistry.createRegistry(9527);
            Registry registry = LocateRegistry.getRegistry(9527);
            registry.bind("winson-remote", remoteMath);
            System.out.println("remote server bind success");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        }


    }

}

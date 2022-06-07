package com.winson.jdkapi.rmi.v2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author winson
 * @date 2022/4/27
 **/
public class MyRemoteClient {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry(9527);
//            IRemoteMath iRemoteMath = (IRemoteMath) registry.lookup("winson-remote");
            String path = "rmi://172.16.2.113:9527/winson-remote";
//            IRemoteMath iRemoteMath = (IRemoteMath) registry.lookup(path);
            IRemoteMath iRemoteMath = (IRemoteMath) Naming.lookup(path);
//            iRemoteMath.sayHello("winson");
            System.out.println(iRemoteMath.add(1, 3));

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

}

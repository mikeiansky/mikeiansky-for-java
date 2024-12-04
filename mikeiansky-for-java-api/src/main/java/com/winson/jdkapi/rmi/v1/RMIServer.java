package com.winson.jdkapi.rmi.v1;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author winson
 * @date 2021/12/27
 **/
public class RMIServer {

    // RMI服务器IP地址
    public static final String RMI_HOST = "172.16.2.113";
    // RMI服务端口
    public static final int RMI_PORT = 9527;
    // RMI服务名称
    public static final String RMI_NAME = "rmi://" + RMI_HOST + ":" + RMI_PORT + "/AAAAAAA";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        // 注册RMI服务端口
        LocateRegistry.createRegistry(RMI_PORT);
        // 绑定对应的Remote对象（这里就是你的RMITestImpl对象）
//        Naming.bind(RMI_NAME, new RMITestImpl());
        System.out.println("RMI服务启动成功,服务地址:" + RMI_NAME);
    }

}

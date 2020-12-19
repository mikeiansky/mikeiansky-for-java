package com.winson.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author winson
 * @date 2020/12/17
 **/
public class TestNet {

    public static void main(String[] args) {
        System.out.println("测试获取ip地址====》开始");
        System.out.println("--------①--------");
        System.out.println("第一种方式获取IP地址，InetAddress.getLocalHost()");
        try {
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            System.out.println("第一种方式获取IP地址:" + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------②--------");
        System.out.println("第二种方式获取IP地址，NetworkInterface");
        System.out.println("本机IP:" + getIpAddress());
        System.out.println("测试获取ip地址====》结束");
    }

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e.toString());
        }
        return "";
    }


}

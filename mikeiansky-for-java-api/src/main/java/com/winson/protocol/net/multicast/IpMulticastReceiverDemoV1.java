package com.winson.protocol.net.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author winson
 * @date 2022/5/24
 * @desc ip组播测试
 **/
public class IpMulticastReceiverDemoV1 {

    public static void main(String[] args) throws IOException {

        MulticastSocket multicastSocket = new MulticastSocket(10001);
        multicastSocket.joinGroup(InetAddress.getByName("230.0.0.1"));

        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            multicastSocket.receive(packet);
            System.out.println("receiver-1 receive data is : "+new String(packet.getData(), 0, packet.getLength()));
        }

    }

}

package com.winson.protocol.net.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/5/24
 **/
public class IpMulticastSenderDemoV1 {

    public static void main(String[] args) throws IOException {
        System.out.println("send begin ---> ");
        MulticastSocket socket = new MulticastSocket();
        InetAddress address = InetAddress.getByName("230.0.0.1");
        int port = 10001;
        socket.joinGroup(address);
        byte[] data = "1234678xyz".getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(data,data.length, address, port);
        socket.send(packet);
        System.out.println("send begin success ");

    }

}

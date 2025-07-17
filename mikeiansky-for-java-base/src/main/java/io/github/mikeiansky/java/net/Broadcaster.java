package io.github.mikeiansky.java.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author mike ian
 * @date 2025/7/17
 * @desc
 **/
public class Broadcaster {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            String message = "Hello, this is a broadcast message!";
            byte[] buffer = message.getBytes();

            // 255.255.255.255 是广播地址（也可以改为本地网段如 192.168.1.255）
            InetAddress broadcastAddr = InetAddress.getByName("255.255.255.255");
            int port = 8888;

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddr, port);
            socket.send(packet);
            System.out.println("Broadcast sent: " + message);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

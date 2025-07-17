package io.github.mikeiansky.java.net;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

/**
 *
 * @author mike ian
 * @date 2025/7/17
 * @desc
 **/
public class MulticastSender {

    public static void main(String[] args) {
        final String MULTICAST_ADDRESS = "238.9.9.9";
        final int PORT = 4446;

        try (MulticastSocket socket = new MulticastSocket()) {

            // 找到可用的组播接口
            NetworkInterface ni = null;
            for (NetworkInterface nif : java.util.Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (nif.isUp() && nif.supportsMulticast() && !nif.isLoopback()) {
                    ni = nif;
//                    break;
                }
            }

            if (ni == null) {
                System.err.println("没有找到可用的网络接口来发送多播消息。");
                return;
            }

            socket.setNetworkInterface(ni);

            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            String message = "Hello from multicast sender at " + System.currentTimeMillis();

            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);

            socket.send(packet);

            System.out.println("组播消息已发送到 " + MULTICAST_ADDRESS + ":" + PORT + " via " + ni.getDisplayName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

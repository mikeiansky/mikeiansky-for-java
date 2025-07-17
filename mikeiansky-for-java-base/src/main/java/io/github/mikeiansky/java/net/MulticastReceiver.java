package io.github.mikeiansky.java.net;

import java.net.*;

/**
 *
 * @author mike ian
 * @date 2025/7/17
 * @desc
 **/
public class MulticastReceiver {

    public static void main(String[] args) {
        final String MULTICAST_ADDRESS = "238.9.9.9";
        final int PORT = 4446;

        try (MulticastSocket socket = new MulticastSocket(PORT)) {

            // 找到一个支持多播并启用的网络接口
            NetworkInterface ni = null;
            for (NetworkInterface nif : java.util.Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (nif.isUp() && nif.supportsMulticast() && !nif.isLoopback()) {
                    ni = nif;
                    System.out.println("nif : " + nif.getInetAddresses().nextElement());
//                    break;
                }
            }

            if (ni == null) {
                System.err.println("没有找到合适的网络接口来加入组播组。");
                return;
            }

            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            socket.joinGroup(new InetSocketAddress(group, PORT), ni);

            System.out.println("已加入多播组: " + MULTICAST_ADDRESS + " on interface " + ni.getDisplayName());

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("接收到来自 " + packet.getAddress().getHostAddress() + " 的消息: " + received);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package io.github.mikeiansky.java.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author mike ian
 * @date 2025/7/17
 * @desc
 **/
public class Receiver {

    public static void main(String[] args) {
        try {
            int port = 8888;
            DatagramSocket socket = new DatagramSocket(port);
            socket.setReuseAddress(true);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting for broadcast on port " + port + "...");

            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received broadcast from " +
                        packet.getAddress().getHostAddress() + ": " + received);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

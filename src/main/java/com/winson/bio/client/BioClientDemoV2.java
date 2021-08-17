package com.winson.bio.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class BioClientDemoV2 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 20002));
            System.out.println(" connect success : " + socket);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            int index = 1;
            String suffix = "\r\n";
            String prefix = "request index : " ;
            String msg = prefix + index + suffix;
            out.write(msg.getBytes());
            out.flush();
            System.out.println("write success .. ");
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++index;
                msg = prefix + index + suffix;
                out.write(msg.getBytes());
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

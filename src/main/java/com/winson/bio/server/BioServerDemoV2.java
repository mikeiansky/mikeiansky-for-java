package com.winson.bio.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class BioServerDemoV2 {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(20002);

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("receive socket : " + socket);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null){
                    System.out.println("request line : " + line);
                    String response = "resposne -> " + line + "\r\n";
                    out.write(response.getBytes());
                    out.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

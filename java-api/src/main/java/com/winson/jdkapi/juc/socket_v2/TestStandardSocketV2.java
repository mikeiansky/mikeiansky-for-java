package com.winson.jdkapi.juc.socket_v2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

/**
 * @author winson
 * @date 2021/5/17
 **/
public class TestStandardSocketV2 {

    public static class Step1Server{

        ServerSocket serverSocket;

        Function<String, String> handler;

        public Step1Server(Function<String, String> handler) {
            this.handler = handler;
        }

        public void listen(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            while (true){
                accept();
            }
        }

        public void accept(){
            try {
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = br.readLine();
                StringBuilder request = new StringBuilder();
                while (line != null && !line.trim().equals("")) {
                    request.append(line);
                    request.append("\n");
                    line = br.readLine();
                }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String response = handler.apply(request.toString());
                writer.write(response);
                writer.flush();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        Step1Server step1Server = new Step1Server(new Function<String, String>() {
            @Override
            public String apply(String s) {
                System.out.println(s);
                return "HTTP/1.1 200 ok\n\nHello Winson!\n";
            }
        });
        step1Server.listen(8001);
    }

}

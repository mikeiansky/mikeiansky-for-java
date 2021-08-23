package com.winson.jdkapi.io.nio.common;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioDemoV2 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer);
        byte[] bytes = new byte[]{1,2,3};
        buffer.put(bytes);
        System.out.println(buffer);
        buffer.compact();
        System.out.println(buffer);
        buffer.put(bytes);
        System.out.println(buffer);
        System.out.println("-------------");
//        writeData();
        accessFile();
    }

    public static String getFilePath(){
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return filePath += "/winson_out.txt";
    }

    public static void writeData(){
        File file = new File(getFilePath());
        try {
            FileOutputStream out = new FileOutputStream(file);
            try {
                out.write("winson--->123456".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("write finish ... ");
    }

    public static void accessFile(){

        try {
            RandomAccessFile raf = new RandomAccessFile(getFilePath(),"rw");

            try {
                raf.write("123456".getBytes());
//                System.in.read();

                raf.seek(1);
                raf.write("abc".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }

            FileChannel fileChannel = raf.getChannel();
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0 , 10);
            buffer.put("winson".getBytes());
            buffer.force();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

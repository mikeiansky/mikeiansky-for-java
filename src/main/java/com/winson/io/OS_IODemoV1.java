package com.winson.io;

import java.io.*;

/**
 * @author winson
 * @date 2021/8/18
 **/
public class OS_IODemoV1 {

    public static final String path = "D:\\out.txt";
    public static byte[] data = "123456789\n".getBytes();

    public static void fileIO() {

        try {
            FileOutputStream out = new FileOutputStream(path);
            while (true){
                Thread.sleep(10);
                out.write(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("write file finish ... ");

    }

    public static void bufferFileIO(){
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
            while (true){
                Thread.sleep(10);
                out.write(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void randomAccessFileIo(){

        try {
            RandomAccessFile raf = new RandomAccessFile(path, "rw");

            raf.write("Hello winson\n".getBytes());
            raf.write("hello ciwei\n".getBytes());
            System.out.println("------------------ write ------------- ");

            System.in.read();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        fileIO();
//        bufferFileIO();
        randomAccessFileIo();

        System.out.println(" app finish ... ");
    }

}

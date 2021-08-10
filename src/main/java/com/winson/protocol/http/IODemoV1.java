package com.winson.protocol.http;

import java.io.*;

/**
 * @author winson
 * @date 2021/8/10
 **/
public class IODemoV1 {

    public static void readWithReader() {
        System.out.println("---------- read with reader start ----------");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/a.txt")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("---------- read with reader end   ----------");
    }

    public static void readWithInputStream() {
        System.out.println("--------- read with input stream start ---------- ");
        try {
            FileInputStream in = new FileInputStream("D:/a.txt");
            byte[] buf = new byte[1024];
            int length = in.read(buf, 0, buf.length);
            String content = new String(buf, 0, buf.length);
            System.out.println("read length : " + length);
            System.out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------- read with input stream end  ---------- ");
    }

    public static void main(String[] args) {
//        String lineSeparator = System.getProperty("line.separator", "\r");
//        String result = "aaaa" + lineSeparator + "bbbbb" + lineSeparator;
//        System.out.println(result);
//        System.out.println(result.length());

//        System.out.println("---------");
        try {
            FileOutputStream out = new FileOutputStream("D:/a.txt");
            out.write(new byte[]{0x31});
//            out.write(new byte[]{(byte) 0xC0, (byte) 0xB1});
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        readWithReader();
//        readWithInputStream();


    }


}

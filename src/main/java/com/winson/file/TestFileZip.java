package com.winson.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author winson
 * @date 2021/5/26
 **/
public class TestFileZip {

    public static final String DIR = "D:\\work\\temp";

    public static void createFile() throws IOException {
        byte[] buf = new byte[1024 * 1024 * 10];
        FileInputStream in = new FileInputStream(DIR + File.separator + "temp.pdf");
        int readLength = in.read(buf, 0, in.available());
        for (int i = 0; i < 1000; i++) {
            FileOutputStream out = new FileOutputStream(DIR + File.separator + "temp" + (i + 1) + ".pdf");
            out.write(buf, 0, readLength);
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("test file zip start ... ");
//        createFile();
        zipFile();
        System.out.println("test file zip stop ... ");
    }

    public static void zipFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\temp.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        File srcDir = new File(DIR);
        byte[] buffer = new byte[1024 * 1024 * 10];
        int size = srcDir.listFiles().length;
        int index = 0;
        for (File file : srcDir.listFiles()) {
            index++;
            System.out.println("zip start file : " + file.getName());
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream in = new FileInputStream(file);
            int readLength = 0;
            while ((readLength = in.read(buffer, 0, buffer.length)) > 0) {
                zipOutputStream.write(buffer, 0, readLength);
            }
            in.close();
            zipOutputStream.closeEntry();
            System.out.println("zip complete file : " + file.getName());
            if (index >= size) {
                System.out.println("准备读取输入数据。。。");
                System.in.read();
                System.out.println("准备读取输入数据完成。。。");
            }
        }

        zipOutputStream.flush();
        zipOutputStream.close();
        System.in.read();
    }

}

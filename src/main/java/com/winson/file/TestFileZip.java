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
        writeToZip("", new File(DIR), zipOutputStream);
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    public static void writeToZip(String parentPath, File file, ZipOutputStream out) throws IOException {
        if (file.isDirectory()) {
            File[] subFileList = file.listFiles();
            for (File subFile : subFileList) {
                writeToZip(parentPath + File.separator + file.getName(), subFile, out);
            }
        } else {
            String filePath = parentPath + File.separator + file.getName();
            byte[] buffer = new byte[1024 * 1024 * 10];
            out.putNextEntry(new ZipEntry(filePath));
            FileInputStream in = new FileInputStream(file);
            int readLength = 0;
            while ((readLength = in.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, readLength);
            }
            in.close();
            out.closeEntry();
        }
    }

}

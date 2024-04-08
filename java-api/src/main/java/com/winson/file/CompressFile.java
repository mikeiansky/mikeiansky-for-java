package com.winson.file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author winson
 * @date 2022/7/13
 **/
public class CompressFile {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        File f1 = new File("D:\\temp\\f1.zip");
        File f2 = new File("D:\\temp\\f2.zip");
        File f3 = new File("D:\\temp\\f3.zip");
        File f4 = new File("D:\\temp\\f3.zip");
        File f5 = new File("D:\\temp\\f3.zip");
        File f6 = new File("D:\\temp\\f3.zip");

//        FileInputStream in1 = new FileInputStream(f1);
//        FileInputStream in2 = new FileInputStream(f2);
//        FileInputStream in3 = new FileInputStream(f3);
//
//        FileInputStream[] fileInList = new FileInputStream[3];
//        fileInList[0] = in1;
//        fileInList[1] = in2;
//        fileInList[2] = in3;

//        ZipFile zf = new ZipFile("D:\\temp\\aaa-001.zip");
        FileOutputStream out = new FileOutputStream("D:\\temp\\aaa-001.zip");
        ZipOutputStream zos = new ZipOutputStream(out);

//        for (FileInputStream fileInputStream : fileInList) {
//
//        }
//
//        for (FileInputStream fileInputStream : fileInList) {
//            fileInputStream.close();
//        }

        writeToZip("", f1, zos);
        writeToZip("", f2, zos);
        writeToZip("", f3, zos);

        zos.close();
        long end = System.currentTimeMillis();
        System.out.println("complete --- > use time : " + (end - start));
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

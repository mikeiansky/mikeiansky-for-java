package com.winson.jdkapi.io.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/6/22
 * @desc 随机读写
 **/
public class RandomRWFileDemoV1 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile raf = new RandomAccessFile("D:\\test.txt", "rw");

//        raf.seek(8);
        raf.write("2-Hello-001".getBytes(StandardCharsets.UTF_8));

        raf.close();

        System.out.println("main app complete ... ");
    }

}

package com.winson.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.file.CopyOption;
import java.nio.file.spi.FileSystemProvider;

/**
 * @author winson
 * @date 2021/6/9
 **/
public class TestIO {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("test io start ... ");
        ByteBuffer bf = null;
        bf.rewind();
        new FileInputStream("/hello/yellow").getChannel();
        FileSystemProvider provider = null;

        System.out.println("test io stop ... ");
    }

}

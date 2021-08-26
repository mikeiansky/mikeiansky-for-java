package com.winson.temp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author winson
 * @date 2021/8/24
 **/
public class OSFileIO {

    public static void main(String[] args) throws Exception {
        String arg0 = "0";
        if(args!=null && args.length > 0){
            arg0 = args[0];
        }

        switch (arg0){
            case "0":
                writeFile();
                break;
            case "1":
                readFile();
                break;
        }
    }

    public static void writeFile() throws Exception{
        String filePath = OSFileIO.class.getResource("").getPath() + "out.txt";
        FileOutputStream out = new FileOutputStream(filePath);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        while (true) {
//            Thread.sleep(10);
//            out.write("1234567890\r\n".getBytes());
            bout.write("1234567890\r\n".getBytes());
        }
    }

    private static void readFile() throws Exception{
        RandomAccessFile raf = new RandomAccessFile("out.txt","rw");
        System.out.println("create raf ... ");
        System.in.read();

        raf.write("1234567890\r\n".getBytes());
        raf.write("hello,world\r\n".getBytes());
        System.out.println("write init message ... ");
        System.in.read();

        raf.seek(4);
        raf.write("xx00".getBytes());
        System.out.println("seek and write ...");
        System.in.read();

        FileChannel fileChannel = raf.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0, 1024);
        System.out.println("create mapper buffer for file!");
        System.in.read();
        mappedByteBuffer.put("--".getBytes());
        System.out.println("mapper buffer put byte complete");
        System.in.read();

    }


}

package com.winson.juc.socket_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author winson
 * @date 2021/5/14
 **/
public class TestByteBuffer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("获取连接 。。。。 ");
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.println("before read ... ");
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());

            int readLength = socketChannel.read(buffer);
            System.out.println("after read1 ... and read length : " + readLength);
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());

            byte[] buf = new byte[1024];
            buffer.flip();
            System.out.println("after flip ... ");
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());

            buffer.get(buf, 0, readLength);
            System.out.println("after read1 ... ");
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());

            buffer.flip();
            byte[] buf2 = new byte[1024];
            buffer.get(buf2, 0, readLength);
            String sb2 = new String(buf2);
            System.out.println("read content2 is : " + sb2);
            System.out.println("after get2 ... ");
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());
//            buffer.flip();
            buffer.get();
            System.out.println("after gg1 ... ");
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());

            buffer.get();
            System.out.println("after gg2 ... ");
            System.out.println("position : " + buffer.position() + " , limit : " + buffer.limit() + " , capacity : " + buffer.capacity());


            String sb = new String(buf);
            System.out.println("read content is : " + sb);
            CharBuffer cb = buffer.asCharBuffer();
            System.out.println(cb.toString());
            System.out.println("once read");
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            buffer2.put("HTTP/1.1 200 ok\n\nHello World\n".getBytes());
            buffer2.flip();
            socketChannel.write(buffer2);
            socketChannel.close();

        }
    }

}

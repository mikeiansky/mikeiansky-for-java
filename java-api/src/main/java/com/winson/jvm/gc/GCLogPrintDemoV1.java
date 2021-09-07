package com.winson.jvm.gc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/9/2
 **/
public class GCLogPrintDemoV1 {

    public static final int _1MB = 1024 * 1024;

    /**
     * 设置gc大小
     * <p>
     *      -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
     *      -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
     *      -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
     *
     * -XX:+PrintGC -XX:+PrintGCDetails -Xms20m -Xmx20m -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("hello jvm gc start ");

        byte[] b1 = new byte[_1MB];
        byte[] b2 = new byte[_1MB * 2];
        byte[] b3 = new byte[_1MB * 2];
        byte[] b4 = new byte[_1MB * 2];
        byte[] b5 = new byte[_1MB * 2];
        b5 = null;
        byte[] b6 = new byte[_1MB * 2];
        byte[] b7 = new byte[_1MB * 2];
        byte[] b8 = new byte[_1MB * 2];
        byte[] b9 = new byte[_1MB * 2];
//        byte[] b10 = new byte[_1MB * 2];

        CountDownLatch latch = new CountDownLatch(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("demo start");
//                try {
//                    latch.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                for (;;) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println("demo end");
            }
        }, "winson-thread");
        thread.setPriority(5);
        thread.setDaemon(true);
        thread.start();

        // 测试元空间内存回收异常
        int size = 700;
        ArrayList<Object> objList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ClassLoader loader = new ClassLoader() {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                    String fileName1 = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream in = getClass().getResourceAsStream(fileName1);
                    if(in != null){
                        try {
                            byte[] cb = new byte[in.available()];
                            in.read(cb);
                            return defineClass(name, cb, 0, cb.length);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return super.loadClass(name);
                }
            };
            Class clazz = loader.loadClass(GCLogPrintDemoV1.class.getName());
            objList.add(clazz);
//            System.out.println(loader.loadClass(GCLogPrintDemoV1.class.getName()));
        }
        System.out.println("app run end ... ");
        try {
            int flag = 0;
            while (true){
                flag++;
                System.out.println(sayHello(String.valueOf(flag)));
                System.in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("hello jvm gc end");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sayHello(String msg){
        return "hello : " + msg;
    }

}

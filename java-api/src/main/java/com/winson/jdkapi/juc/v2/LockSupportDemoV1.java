package com.winson.jdkapi.juc.v2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class LockSupportDemoV1 {


    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("LockSupport classLoader:" + LockSupport.class.getClassLoader());
        System.out.println("LockSupportDemoV1 classLoader:" + LockSupportDemoV1.class.getClassLoader());
//        sun.misc.Unsafe UNSAFE = sun.misc.Unsafe.getUnsafe();
        Field[] fields = sun.misc.Unsafe.class.getDeclaredFields();

//        System.out.println(fields.length);
        sun.misc.Unsafe UNSAFE = null;
        for (Field field : fields) {
            if(field.getName().equals("theUnsafe")){
                field.setAccessible(true);
                UNSAFE = (Unsafe) field.get(Unsafe.class);
                break;
            }
        }
//        System.out.println(UNSAFE);

        System.out.println("lock support demo v1 start ");
        Thread main = Thread.currentThread();
        final Object lock = new Object();
        Unsafe finalUNSAFE = UNSAFE;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 两种方式都可以
                LockSupport.unpark(main);
//                finalUNSAFE.unpark(main);

            }
        }).start();
//        LockSupport.park(lock);
        LockSupport.parkNanos(1000000000);

        System.out.println("lock support demo v1 complete ");
    }

}

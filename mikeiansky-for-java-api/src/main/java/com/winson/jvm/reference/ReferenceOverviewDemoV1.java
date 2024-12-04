package com.winson.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author winson
 * @date 2021/9/13
 **/
public class ReferenceOverviewDemoV1 {

    public static class MyPhantomObject{

    }

    public static void main(String[] args) throws InterruptedException {

        ReferenceQueue<MyPhantomObject> mq = new ReferenceQueue<>();
        PhantomReference<MyPhantomObject> pm = new PhantomReference<>(
                new MyPhantomObject(),mq
        );

        System.out.println(mq);
        System.out.println(pm);
        System.out.println(pm.get());
//        System.out.println(mq.poll());
        System.out.println(mq.remove(1000));

        

    }

}

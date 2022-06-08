package com.winson.netty.echo;

import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author winson
 * @date 2022/6/8
 **/
public class MyErrorGenericFutureListener implements GenericFutureListener<MyErrorFuture> {

    @Override
    public void operationComplete(MyErrorFuture future) throws Exception {
        System.out.println("operationComplete do with my error future");
        System.out.println("operationComplete --> " + future);
    }

}

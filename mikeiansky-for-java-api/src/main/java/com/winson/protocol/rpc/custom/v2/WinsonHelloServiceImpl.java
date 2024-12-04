package com.winson.protocol.rpc.custom.v2;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonHelloServiceImpl {

    public String sayHello(String message) {
        System.out.println("winson say hello and message is : " + message);
        return "winson echo : " + message;
    }

}

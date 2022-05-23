package com.winson.protocol.rpc.custom.v2;


import java.io.*;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcTestV2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        WinsonRpcHeader header = new WinsonRpcHeader();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(header);
        System.out.println(out.toByteArray().length);
//        String message = "hello world!";
//        byte[] body = message.getBytes(StandardCharsets.UTF_8);
//        System.out.println(body.length);

//        ByteArrayOutputStream bodyOut = new ByteArrayOutputStream();
//        WinsonRpcBody rpcBody = new WinsonRpcBody();
//        rpcBody.setServiceName("helloService");
//        rpcBody.setMethodName("sayHello");
//        rpcBody.setClazz("com.winson.protocol.rpc.custom.v1.WinsonHelloService");
//        rpcBody.setArgs(new Object[]{"ciwei"});
//        ObjectOutputStream bodyObjOut = new ObjectOutputStream(bodyOut);
//        bodyObjOut.writeObject(rpcBody);
//        byte[] bodyByteArray = bodyOut.toByteArray();
//
//
//        ByteArrayInputStream bodyIn = new ByteArrayInputStream(bodyByteArray);
//        ObjectInputStream bodyObjIn = new ObjectInputStream(bodyIn);
//        WinsonRpcBody winsonRpcBody = (WinsonRpcBody) bodyObjIn.readObject();
//        System.out.println("winsonRpcBody ---> " + winsonRpcBody);


    }

}

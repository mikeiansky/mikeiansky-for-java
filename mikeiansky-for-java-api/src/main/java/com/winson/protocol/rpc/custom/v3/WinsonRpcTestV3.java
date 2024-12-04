package com.winson.protocol.rpc.custom.v3;


import java.io.*;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcTestV3 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);

//        WinsonRpcHeader header = new WinsonRpcHeader();
//        objOut.writeObject(header);
//        System.out.println(out.toByteArray().length);

        WinsonRpcResponseHeader responseHeader = new WinsonRpcResponseHeader();
        objOut.writeObject(responseHeader);
//        String test = "hello world";
//        objOut.writeObject(test);
        byte[] obts = out.toByteArray();
        System.out.println(obts.length);

//        ByteArrayInputStream bin = new ByteArrayInputStream(obts);
//        ObjectInputStream objIn = new ObjectInputStream(bin);
//        Object temp = objIn.readObject();
//        System.out.println("temp : " + temp);

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

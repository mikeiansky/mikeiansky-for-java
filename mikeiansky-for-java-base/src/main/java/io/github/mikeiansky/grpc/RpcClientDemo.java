package io.github.mikeiansky.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;


public class RpcClientDemo {

        //使用main方法来测试client端
        public static void main(String[] args) throws Exception {
            RpcClientDemo clientDemo = new RpcClientDemo();
            try {
                //基于gRPC远程调用对应的方法
                clientDemo.remoteCall("【mikeian】");
            } finally {
            }
        }
        /**
         * 基于gRPC框架的使用步骤，进行远程调用
         * @param name
         */
        public void remoteCall(String name) {
            HelloRequest request = HelloRequest.newBuilder().setName(name).build();
            HelloResponse response;
            try {
                // 基于访问地址 创建通道
                Channel channel =  ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
                // 利用通道 创建一个桩（Stub）对象
                HelloServiceGrpc.HelloServiceBlockingStub blockingStub = HelloServiceGrpc.newBlockingStub(channel);
                //通过桩对象来调用远程方法
                response = blockingStub.sayHello(request);
            } catch (StatusRuntimeException e) {
                return;
            }
            System.out.println("client端远程调用sayHello()的结果为：\n\n" + response.getMessage());
        }

}

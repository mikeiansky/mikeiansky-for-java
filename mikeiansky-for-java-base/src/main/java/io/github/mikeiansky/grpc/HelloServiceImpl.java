package io.github.mikeiansky.grpc;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {



    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        super.sayHello(request, responseObserver);
    }

}

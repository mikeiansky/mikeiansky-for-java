package io.github.mikeiansky.grpc;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {



    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        super.sayHello(request, responseObserver);
    }

}

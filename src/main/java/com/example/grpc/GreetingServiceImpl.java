package com.example.grpc;

import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseStreamObserver) {
        //HelloRequest has toString auto-generated
        System.out.println(request);

        //use a builder to construct a new protobuffer object
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello there, " + request.getName())
                .build();

        //responseObserver used to send a single response back
        responseStreamObserver.onNext(response);

        // response has been sent, so it is now required to call onCompleted
        responseStreamObserver.onCompleted();
    }
}

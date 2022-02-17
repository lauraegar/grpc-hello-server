package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String [] args) throws Exception {
        //channel is the abstraction to connect to a service endpoint
        //use plaintext communication because we don't have security certificates

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext(true)
                .build();

        //up to the client to determine whether to block the call
        //create a blocking async stub
        // or an async stub with Future are always possible.
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest.newBuilder()
                        .setName("Laura")
                        .build();

      //make the call using the stub
      GreetingServiceOuterClass.HelloResponse response =
      stub.greeting(request);

      System.out.println(response);

      //channel should be shutdown before stopping the process
      channel.shutdownNow();
    }
}

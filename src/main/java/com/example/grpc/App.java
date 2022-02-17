package com.example.grpc;

/* this is a simple server that listens on a port and registers the greeting service implementation */

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class App {
    public static void main( String[] args ) throws Exception  {
        //create a new server and listen on port 8080
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl())
                .build();

        //start the server
        server.start();

        //server threads running in the background
        System.out.println("server started...");
        //don't exit main, await termination
        server.awaitTermination();
    }
}

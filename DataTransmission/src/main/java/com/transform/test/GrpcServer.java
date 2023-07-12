package com.transform.test;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) {
        Server server = null;
        try{
            server = ServerBuilder.forPort(8888)
                    .addService(new MyRPCDateServiceImpl())
                    .build().start();
            server.awaitTermination();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (server!=null) {
                server.shutdown();
            }
        }
    }
}
